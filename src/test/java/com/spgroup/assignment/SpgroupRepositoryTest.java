package com.spgroup.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.spgroup.assignment.model.Users;
import com.spgroup.assignment.repository.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SpgroupRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UsersRepository usersRepository;
	
	@Test
	public void whenFindByEmailId_thenReturnUserNullIfNotExists() {
	    Users user = usersRepository.findUserByEmailId("NotPresent@gmail.com");
	    assertThat(user).isNull();
	}
	
	@Test
	public void saveNewUser() {
		Users userNew = new Users();
		userNew.setEmailId("SaveEmail@gmail.com");
		userNew.setCreatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
		userNew.setCreatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
	    Users user = usersRepository.save(userNew);
	    assertThat(user.getUserId()).isNotNull();
	}
	
	@Test
	public void whenFindByEmailId_thenReturnUserIfExists() {
		saveNewUser();
	    Users user = usersRepository.findUserByEmailId("SaveEmail@gmail.com");
	    assertThat(user).isNotNull();
	}

}
