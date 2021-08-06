package com.linkedin.metadata.dao.internal;

import com.google.protobuf.ByteString;
import com.linkedin.common.urn.Urn;
import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.validator.EntityValidator;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphProto.Mutation;
import io.dgraph.DgraphProto.Request;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import static com.linkedin.metadata.dao.utils.ModelUtils.*;

@Slf4j
public class DgraphGraphWriterDAO extends BaseGraphWriterDAO {

    private final DgraphClient _client;

    public DgraphGraphWriterDAO(@Nonnull DgraphClient client) {
        this._client = client;
    }

    private <ENTITY extends RecordTemplate> String getQueryForEntity(int idx, ENTITY entity) {
        return String.format("entity%d as var(func: eq(urn, \"%s\"))", idx, getUrnFromEntity(entity));
    }

    private <ENTITY extends RecordTemplate> String getEntityMutation(int idx, ENTITY entity) {
        StringJoiner mutation = new StringJoiner("\n");
        entity.data().entrySet().stream().map(e ->
                String.format("uid(entity%d) <%s> %s .", idx, e.getKey(), getNquadValue(e.getValue()))
        ).forEach(mutation::add);
        return mutation.toString();
    }

    private String getNquadValue(Object value) {
        return "\"" + value + "\"";
    }

    @Override
    public <ENTITY extends RecordTemplate> void addEntities(@Nonnull List<ENTITY> entities) throws Exception {
        StringJoiner queries = new StringJoiner("\n");
        StringJoiner mutations = new StringJoiner("\n");

        IntStream.range(0, entities.size()).forEach(idx -> {
            ENTITY entity = entities.get(idx);
            EntityValidator.validateEntitySchema(entity.getClass());
            queries.add(getQueryForEntity(idx + 1, entity));
            mutations.add(getEntityMutation(idx + 1, entity));
        });

        String query = String.format("query {\n"
                + "%s\n"
                + "}", queries);

        log.debug("Query: " + query);
        log.debug("Mutations: " + mutations);

        // construct the upsert
        Mutation mutation = Mutation.newBuilder()
                .setSetNquads(ByteString.copyFromUtf8(mutations.toString()))
                .build();
        Request request = Request.newBuilder()
                .setQuery(query)
                .addMutations(mutation)
                .setCommitNow(true)
                .build();

        // run the request
        this._client.newTransaction().doRequest(request);
    }

    @Override
    public <URN extends Urn> void removeEntities(@Nonnull List<URN> urns) throws Exception {

    }

    @Override
    public <RELATIONSHIP extends RecordTemplate>
    void addRelationships(@Nonnull List<RELATIONSHIP> relationships, @Nonnull RemovalOption removalOption) throws Exception {

    }

    @Override
    public <RELATIONSHIP extends RecordTemplate> void removeRelationships(@Nonnull List<RELATIONSHIP> relationships) throws Exception {

    }
}
