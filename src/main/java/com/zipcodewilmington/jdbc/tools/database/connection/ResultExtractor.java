package com.zipcodewilmington.jdbc.tools.database.connection;

import java.sql.ResultSet; /**
 * @author leon on 3/24/18.
 */
public interface ResultExtractor<T> {
    T extract(ResultSet resultSet);
}
