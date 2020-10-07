package vespinoza.primerejemplo.primerspring;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vespinoza.primerejemplo.primerspring.entity.UserDemo;
import vespinoza.primerejemplo.primerspring.repository.UserRepository;

@SpringBootTest
class PrimerspringApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Test
	void crearUsuarioTest() {
		UserDemo usuario = new UserDemo();
		int id = 2;
		long lid = id;
		usuario.setId(lid);
		usuario.setNombre("VictorH");
		usuario.setClave(encoder.encode("abc123"));
		UserDemo retorno = userRepository.save(usuario);
		assertTrue(retorno.getClave().equalsIgnoreCase(usuario.getClave()));

	}
	
}
