/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.dbtest.cases.authority.sql;

import io.shardingsphere.core.constant.DatabaseType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Authority SQL set xml entry.
 *
 * @author panjuan
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public final class SQLSet {
    
    @XmlAttribute(name = "db-types")
    private String dbTypes;
    
    @XmlAttribute(name = "sql-type")
    private SQLType sqlType;
    
    @XmlElement(name = "sql")
    private Collection<SQL> SQLs = new LinkedList<>();
    
    private Collection<DatabaseType> getDatabaseTypeList() {
        if (null == dbTypes) {
            return Arrays.asList(DatabaseType.MySQL, DatabaseType.Oracle, DatabaseType.H2, DatabaseType.PostgreSQL, DatabaseType.SQLServer);
        }
        Collection<DatabaseType> result = new LinkedList<>();
        for (String each : dbTypes.split(",")) {
            result.add(DatabaseType.valueOf(each));
        }
        return result;
    }
    
    /**
     * Get all sqls content.
     *
     * @return sqls content
     */
    public Collection<String> getAllSQLContent(final SQLType sqlType, final DatabaseType databaseType ) {
        Collection<String> result = new LinkedList<>();
        if (this.sqlType != sqlType || !getDatabaseTypeList().contains(databaseType)) {
            return result;
        }
        for (SQL each : SQLs) {
            result.add(each.getContent());
        }
        return result;
    }
}
