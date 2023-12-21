package com.progavanzada.taller.mecanico.services;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progavanzada.taller.mecanico.controller.ReservaController;
import com.progavanzada.taller.mecanico.entities.Cliente;
import com.progavanzada.taller.mecanico.entities.Persona;
import com.progavanzada.taller.mecanico.entities.Tecnico;
import com.progavanzada.taller.mecanico.repositories.ClienteRepository;
import com.progavanzada.taller.mecanico.repositories.ReservaRepository;
import com.progavanzada.taller.mecanico.repositories.TecnicoRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservaService service;

    @MockBean
    private ReservaRepository repo;

    @MockBean
    private ClienteRepository clienteRepo;

    @MockBean
    private TecnicoRepository tecnicoRepo;

    private List<Cliente> clientes;

    private List<Tecnico> tecnicos;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        // Crear 2 personas.
        Persona persona1 = new Persona();
        persona1.id = 1;
        persona1.dni = 12345678;
        persona1.name = "Persona1";
        persona1.surName = "Apellido1";
        persona1.email = "persona1.apellido1@test.com";
        persona1.phoneNumber = "+541234567890";
        persona1.street = "Grove Street";
        persona1.streetNumber = 123;

        Persona persona2 = new Persona();
        persona2.id = 2;
        persona2.dni = 88222333;
        persona2.name = "Persona2 Tecnico";
        persona2.surName = "Apellido2";
        persona2.email = "persona2.apellido2@test.com";
        persona2.phoneNumber = "+5493534112233";
        persona2.street = "Jose Ingenieros";
        persona2.streetNumber = 999;

        // Crear 1 cliente
        Cliente cliente1 = new Cliente();
        cliente1.id = 1;
        cliente1.person = persona1;

        // Crear 1 tecnico
        Tecnico tecnico1 = new Tecnico();
        tecnico1.id = 1;
        tecnico1.person = persona2;

        this.clientes = new ArrayList<>();
        this.clientes.add(cliente1);

        this.tecnicos = new ArrayList<>();
        this.tecnicos.add(tecnico1);
    }

    @Test
    public void test_crear_reserva_correcto() throws Exception {
        given(this.clienteRepo.findByIdAndEliminadoFalse(1)).willReturn(clientes.get(0));
        given(this.tecnicoRepo.findByIdAndEliminadoFalse(1)).willReturn(tecnicos.get(0));

        // Crear fechas de inicio y de fin desde la fecha actual.
        java.util.Date fechaActual = new java.util.Date();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fechaInicio = df.format(fechaActual.getTime() + 300000);
        String fechaFin = df.format(fechaActual.getTime() + 2400000);

        // Body de la creacion de reserva.
        Map<String, Object> body = new HashMap<>();
        body.put("client", 1);
        body.put("tecnico", 1);
        body.put("fechaInicio", fechaInicio.toString());
        body.put("fechaFin", fechaFin.toString());

        this.mvc.perform(
                // Hacer el POST de creación.
                post("/reservas").content(this.mapper.writeValueAsString(body)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                // Esperar que el status sea OK (no hubo errores, se creó la reserva)
                .andExpect(status().isOk());
    }

    @Test
    public void test_crear_reserva_fechas_en_pasado() throws Exception {
        given(this.clienteRepo.findByIdAndEliminadoFalse(1)).willReturn(clientes.get(0));
        given(this.tecnicoRepo.findByIdAndEliminadoFalse(1)).willReturn(tecnicos.get(0));

        // Crear fechas de inicio y de fin desde la fecha actual.
        java.util.Date fechaActual = new java.util.Date();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fechaInicio = df.format(fechaActual.getTime() - 300000);
        String fechaFin = df.format(fechaActual.getTime() - 2400000);

        // Body de la creacion de reserva.
        Map<String, Object> body = new HashMap<>();
        body.put("client", 1);
        body.put("tecnico", 1);
        body.put("fechaInicio", fechaInicio.toString());
        body.put("fechaFin", fechaFin.toString());

        this.mvc.perform(
                // Hacer el POST de creación.
                post("/reservas").content(this.mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                // Esperar que el status sea 400 Bad Request (las fechas están en el pasado, o
                // una de ellas lo está)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_crear_reserva_cliente_no_existe() throws Exception {
        given(this.clienteRepo.findByIdAndEliminadoFalse(1)).willReturn(null);
        given(this.tecnicoRepo.findByIdAndEliminadoFalse(1)).willReturn(tecnicos.get(0));

        // Crear fechas de inicio y de fin desde la fecha actual.
        java.util.Date fechaActual = new java.util.Date();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fechaInicio = df.format(fechaActual.getTime() - 300000);
        String fechaFin = df.format(fechaActual.getTime() - 2400000);

        // Body de la creacion de reserva.
        Map<String, Object> body = new HashMap<>();
        body.put("client", 53);
        body.put("tecnico", 1);
        body.put("fechaInicio", fechaInicio.toString());
        body.put("fechaFin", fechaFin.toString());

        this.mvc.perform(
                // Hacer el POST de creación.
                post("/reservas").content(this.mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                // Esperar que el status sea 400 Bad Request (el cliente no existe)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_crear_reserva_fechas_formato_equivocado() throws Exception {
        given(this.clienteRepo.findByIdAndEliminadoFalse(1)).willReturn(clientes.get(0));
        given(this.tecnicoRepo.findByIdAndEliminadoFalse(1)).willReturn(tecnicos.get(0));

        // Crear fechas de inicio y de fin desde la fecha actual.
        java.util.Date fechaActual = new java.util.Date();

        // Formattear las fechas como ISO8601 (formato equivocado al que espera el
        // controlador)
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        String fechaInicio = df.format(fechaActual.getTime() - 300000);
        String fechaFin = df.format(fechaActual.getTime() - 2400000);

        // Body de la creacion de reserva.
        Map<String, Object> body = new HashMap<>();
        body.put("client", 53);
        body.put("tecnico", 1);
        body.put("fechaInicio", fechaInicio.toString());
        body.put("fechaFin", fechaFin.toString());

        this.mvc.perform(
                // Hacer el POST de creación.
                post("/reservas").content(this.mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                // Esperar que el status sea 400 Bad Request (el cliente no existe)
                .andExpect(status().isBadRequest());
    }
}
