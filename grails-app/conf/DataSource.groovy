hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/principalesaustro?autoReconnect=true&useSSL=false"
            username = "root"
            password = ""
            pooled = false
            jmxExport = true
            dbCreate = "update"
        }
    }
    production {
        dataSource {
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://172.17.0.4/principalesaustro?autoReconnect=true&useSSL=false"
            username = "usuarioWeb"
            password = "usuarioWeb2016\$%"
            pooled = false
            jmxExport = true
            dbCreate = "update"
        }
    }
}