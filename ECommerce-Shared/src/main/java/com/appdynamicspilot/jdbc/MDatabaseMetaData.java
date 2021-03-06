/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.appdynamicspilot.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

/**
 *
 * @author Owner
 */
public class MDatabaseMetaData implements DatabaseMetaData{

    public boolean allProceduresAreCallable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean allTablesAreSelectable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getURL() throws SQLException {
        return "localhost:1522";
    }

    public String getUserName() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isReadOnly() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nullsAreSortedHigh() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nullsAreSortedLow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nullsAreSortedAtStart() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nullsAreSortedAtEnd() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDatabaseProductName() throws SQLException {
        return "Oracle";
    }

    public String getDatabaseProductVersion() throws SQLException {
        return "10.0.0";
    }

    public String getDriverName() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDriverVersion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getDriverMajorVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getDriverMinorVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean usesLocalFiles() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean usesLocalFilePerTable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean storesUpperCaseIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean storesLowerCaseIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean storesMixedCaseIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getIdentifierQuoteString() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSQLKeywords() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNumericFunctions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getStringFunctions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSystemFunctions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTimeDateFunctions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSearchStringEscape() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getExtraNameCharacters() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsColumnAliasing() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nullPlusNonNullIsNull() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsConvert() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsConvert(int fromType, int toType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsTableCorrelationNames() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsExpressionsInOrderBy() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsOrderByUnrelated() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsGroupBy() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsGroupByUnrelated() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsGroupByBeyondSelect() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsLikeEscapeClause() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsMultipleResultSets() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsMultipleTransactions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsNonNullableColumns() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsMinimumSQLGrammar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCoreSQLGrammar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsExtendedSQLGrammar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsANSI92FullSQL() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsOuterJoins() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsFullOuterJoins() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsLimitedOuterJoins() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSchemaTerm() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getProcedureTerm() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCatalogTerm() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isCatalogAtStart() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCatalogSeparator() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSchemasInDataManipulation() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsPositionedDelete() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsPositionedUpdate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSelectForUpdate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsStoredProcedures() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSubqueriesInComparisons() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSubqueriesInExists() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSubqueriesInIns() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsCorrelatedSubqueries() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsUnion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsUnionAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxBinaryLiteralLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxCharLiteralLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxColumnNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxColumnsInGroupBy() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxColumnsInIndex() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxColumnsInOrderBy() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxColumnsInSelect() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxColumnsInTable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxConnections() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxCursorNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxIndexLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxSchemaNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxProcedureNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxCatalogNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxRowSize() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxStatementLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxStatements() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxTableNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxTablesInSelect() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxUserNameLength() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getDefaultTransactionIsolation() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsTransactions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getSchemas() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getCatalogs() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getTableTypes() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getTypeInfo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsResultSetType(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean ownUpdatesAreVisible(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean ownDeletesAreVisible(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean ownInsertsAreVisible(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean othersUpdatesAreVisible(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean othersDeletesAreVisible(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean othersInsertsAreVisible(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean updatesAreDetected(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deletesAreDetected(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean insertsAreDetected(int type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsBatchUpdates() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Connection getConnection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsSavepoints() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsNamedParameters() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsMultipleOpenResults() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsGetGeneratedKeys() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsResultSetHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetHoldability() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getDatabaseMajorVersion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getDatabaseMinorVersion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getJDBCMajorVersion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getJDBCMinorVersion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getSQLStateType() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean locatorsUpdateCopy() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsStatementPooling() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RowIdLifetime getRowIdLifetime() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getClientInfoProperties() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public ResultSet getPseudoColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean generatedKeyAlwaysReturned() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
