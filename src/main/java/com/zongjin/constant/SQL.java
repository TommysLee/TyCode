package com.zongjin.constant;

/**
 * 各类型数据库表定义SQL
 *
 * @Author Tommy
 * @Date 2015/9/7
 */
public interface SQL {

    /**
     * MySQL 表定义sql
     */
     String MYSQL_TABLE = "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables where TABLE_SCHEMA='{0}' and TABLE_NAME='{1}'";

    /**
     * MySQL 字段定义sql
     */
     String MYSQL_COLUMN = "select TABLE_NAME, COLUMN_KEY, COLUMN_NAME, COLUMN_COMMENT, DATA_TYPE, NUMERIC_PRECISION, NUMERIC_SCALE, COLUMN_DEFAULT, COLUMN_TYPE from information_schema.`COLUMNS` where TABLE_SCHEMA='{0}' and TABLE_NAME='{1}'";

    /**
     * Oracle 表定义sql
     */
     String ORACLE_TABLE = "select TABLE_NAME, COMMENTS from user_tab_comments where TABLE_NAME=upper('{1}')";

    /**
     * Oracle 字段定义sql
     */
     String ORACLE_COLUMN = "select TABLE_NAME,COLUMN_NAME,DATA_TYPE,DATA_LENGTH,DATA_PRECISION,DATA_SCALE,DATA_DEFAULT,(select COMMENTS from USER_COL_COMMENTS ucc where TABLE_NAME=utc.TABLE_NAME and COLUMN_NAME=utc.COLUMN_NAME)COMMENTS from USER_TAB_COLS utc where TABLE_NAME=upper('{1}')";

    /**
     * Oracle 表主键sql
     */
     String ORACLE_PK = "select ucc.TABLE_NAME, ucc.COLUMN_NAME from USER_CONSTRAINTS uc inner join USER_CONS_COLUMNS ucc on uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME and uc.TABLE_NAME=upper('{1}')and uc.CONSTRAINT_TYPE='P'";

    /**
     * SQLServer 表定义sql
     */
     String SQLSERVER_TABLE = "SELECT tab.name AS TABLE_NAME, (SELECT cast([value] as varchar(500)) [value] FROM sys.extended_properties exp WHERE exp.major_id = tab.object_id AND exp.minor_id = 0) AS COMMENTS FROM sys.tables tab WHERE tab.schema_id=1 AND tab.name='{1}'";

    /**
     * SQLServer 字段定义sql
     */
     String SQLSERVER_COLUMN = "SELECT d.NAME TABLE_NAME, a.NAME COLUMN_NAME, ( CASE WHEN ( SELECT count(*) FROM sysobjects WHERE ( NAME IN ( SELECT NAME FROM sysindexes WHERE (id = a.id) AND ( indid IN ( SELECT indid FROM sysindexkeys WHERE (id = a.id) AND ( colid IN ( SELECT colid FROM syscolumns WHERE (id = a.id) AND (NAME = a.NAME))))))) AND (xtype = 'PK')) > 0 THEN 'PRI' ELSE '' END ) COLUMN_KEY, isnull(cast(g.[value] as varchar(500)), ' ') AS [ COLUMN_COMMENT ], b.NAME DATA_TYPE, a.length LENGTH, COLUMNPROPERTY (a.id, a.NAME, 'PRECISION') AS PRECISION, isnull( COLUMNPROPERTY (a.id, a.NAME, 'Scale'), 0 ) AS SCALE, isnull(e.text, '') COLUMN_DEFAULT FROM syscolumns a LEFT JOIN systypes b ON a.xtype = b.xusertype INNER JOIN sysobjects d ON a.id = d.id AND d.xtype = 'U' AND d.NAME <> 'dtproperties' LEFT JOIN syscomments e ON a.cdefault = e.id LEFT JOIN sys.extended_properties g ON a.id = g.major_id AND a.colid = g.minor_id LEFT JOIN sys.extended_properties f ON d.id = f.class AND f.minor_id = 0 WHERE d.NAME = '{1}' ORDER BY a.id, a.colorder";
}
