package com.linkedin.metadata.dao;

import com.linkedin.metadata.dao.internal.DgraphGraphWriterDAO;
import io.dgraph.DgraphClient;

import javax.annotation.Nonnull;

public class DgraphQueryDAOTest extends BaseQueryDAOTestBase<DgraphQueryDAO, DgraphGraphWriterDAO> {
    @Nonnull
    @Override
    DgraphQueryDAO newBaseQueryDao() {
        return new DgraphQueryDAO(new DgraphClient());
    }

    @Nonnull
    @Override
    DgraphGraphWriterDAO newBaseGraphWriterDao() {
        return new DgraphGraphWriterDAO(new DgraphClient());
    }
}
