package com.topclass.schedulesystem;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestDatabase {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testProfessorNotEmpty() {
        int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM professor", Integer.class);
        assertTrue(rowCount > 0, "Table professor is empty");
    }

}