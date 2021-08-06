package com.linkedin.metadata.dao.internal;

import com.linkedin.common.urn.Urn;
import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.dao.DgraphQueryDAO;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;

import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class DgraphGraphWriterDAOTest extends BaseGraphWriterDAOTestBase<DgraphGraphWriterDAO, DgraphQueryDAO> {

  private ManagedChannel _channel;

  @BeforeMethod
  public void init() {
    _channel = ManagedChannelBuilder
            .forAddress("localhost", 9080)
            .usePlaintext()
            .build();
  }

  @AfterMethod
  public void tearDown() {
    _channel.shutdown();
  }

  @Nonnull
  @Override
  DgraphGraphWriterDAO newBaseGraphWriterDao() {
    DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(_channel);
    return new DgraphGraphWriterDAO(new DgraphClient(stub));
  }

  @Nonnull
  @Override
  DgraphQueryDAO newBaseQueryDao() {
    DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(_channel);
    return new DgraphQueryDAO(new DgraphClient(stub));
  }

  @Nonnull
  @Override
  public Optional<Map<String, Object>> getNode(@Nonnull Urn urn) {
    return Optional.empty();
  }

  @Nonnull
  @Override
  public List<Map<String, Object>> getAllNodes(@Nonnull Urn urn) {
    return null;
  }

  @Nonnull
  @Override
  public List<Map<String, Object>> getEdges(@Nonnull RecordTemplate relationship) {
    return null;
  }

  @Nonnull
  @Override
  public List<Map<String, Object>> getEdgesFromSource(@Nonnull Urn sourceUrn, @Nonnull Class<? extends RecordTemplate> relationshipClass) {
    return null;
  }

}
