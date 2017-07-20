package io.switchbit.jugbank.query.customers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class CustomerEntityRepository extends JdbcRepository<CustomerEntity, String> {

	public static final RowMapper<CustomerEntity> ROW_MAPPER = (resultSet,
                                                                rowNum) -> new CustomerEntity(
			        resultSet.getString("customer_id"),
					resultSet.getString("name"),
                    resultSet.getString("surname"));

	public static final RowUnmapper<CustomerEntity> ROW_UNMAPPER = customerEntity -> {
		Map<String, Object> row = new HashMap<>();
		row.put("customer_id", customerEntity.getCustomerId());
		row.put("name", customerEntity.getName());
		row.put("surname", customerEntity.getSurname());
		return row;
	};

	public CustomerEntityRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, "customers", "customer_id");
	}

	@Override
	protected <S extends CustomerEntity> S postUpdate(S entity) {
		entity.setPersisted(true);
		return entity;
	}

	@Override
	protected <S extends CustomerEntity> S postCreate(S entity, Number generatedId) {
		entity.setPersisted(true);
		return entity;
	}
}
