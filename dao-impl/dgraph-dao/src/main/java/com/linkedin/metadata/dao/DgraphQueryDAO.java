package com.linkedin.metadata.dao;

import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.dao.utils.Statement;
import com.linkedin.metadata.query.Filter;
import com.linkedin.metadata.query.RelationshipFilter;
import io.dgraph.DgraphClient;
import org.javatuples.Triplet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DgraphQueryDAO extends BaseQueryDAO {

    private final @Nonnull DgraphClient _client;

    public DgraphQueryDAO(@Nonnull DgraphClient client) {
        this._client = client;
    }

    @Nonnull
    @Override
    public <ENTITY extends RecordTemplate> List<ENTITY> findEntities(@Nonnull Class<ENTITY> entityClass, @Nonnull Filter filter, int offset, int count) {
        return null;
    }

    @Nonnull
    @Override
    public <ENTITY extends RecordTemplate> List<ENTITY> findEntities(@Nonnull Class<ENTITY> entityClass, @Nonnull Statement queryStatement) {
        return null;
    }

    @Nonnull
    @Override
    public List<RecordTemplate> findMixedTypesEntities(@Nonnull Statement queryStatement) {
        return null;
    }

    @Nonnull
    @Override
    public <SRC_ENTITY extends RecordTemplate, DEST_ENTITY extends RecordTemplate, RELATIONSHIP extends RecordTemplate>
    List<RecordTemplate> findEntities(@Nullable Class<SRC_ENTITY> sourceEntityClass,
                                      @Nonnull Filter sourceEntityFilter,
                                      @Nullable Class<DEST_ENTITY> destinationEntityClass,
                                      @Nonnull Filter destinationEntityFilter,
                                      @Nonnull Class<RELATIONSHIP> relationshipType,
                                      @Nonnull RelationshipFilter relationshipFilter,
                                      int minHops, int maxHops,
                                      int offset, int count) {
        return null;
    }

    @Nonnull
    @Override
    public <SRC_ENTITY extends RecordTemplate, RELATIONSHIP extends RecordTemplate, INTER_ENTITY extends RecordTemplate>
    List<RecordTemplate> findEntities(@Nullable Class<SRC_ENTITY> sourceEntityClass,
                                      @Nonnull Filter sourceEntityFilter,
                                      @Nonnull List<Triplet<Class<RELATIONSHIP>, RelationshipFilter, Class<INTER_ENTITY>>> traversePaths,
                                      int offset, int count) {
        return null;
    }

    @Nonnull
    @Override
    public <SRC_ENTITY extends RecordTemplate, DEST_ENTITY extends RecordTemplate, RELATIONSHIP extends RecordTemplate>
    List<RELATIONSHIP> findRelationships(@Nullable Class<SRC_ENTITY> sourceEntityClass,
                                         @Nonnull Filter sourceEntityFilter,
                                         @Nullable Class<DEST_ENTITY> destinationEntityClass,
                                         @Nonnull Filter destinationEntityFilter,
                                         @Nonnull Class<RELATIONSHIP> relationshipType,
                                         @Nonnull Filter relationshipFilter,
                                         int offset, int count) {
        return null;
    }

    @Nonnull
    @Override
    public <RELATIONSHIP extends RecordTemplate>
    List<RELATIONSHIP> findRelationships(@Nonnull Class<RELATIONSHIP> relationshipClass, @Nonnull Statement queryStatement) {
        return null;
    }

    @Nonnull
    @Override
    public List<RecordTemplate> findMixedTypesRelationships(@Nonnull Statement queryStatement) {
        return null;
    }
}
