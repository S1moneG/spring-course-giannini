
SELECT 'CREATE DATABASE local_db_liquibase OWNER simoneg'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'local_db_liquibase'
)\gexec

SELECT 'CREATE DATABASE local_db_flyway OWNER simoneg'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'local_db_flyway'
)\gexec
