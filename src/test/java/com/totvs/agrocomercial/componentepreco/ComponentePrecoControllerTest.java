package com.totvs.agrocomercial.componentepreco;

import com.totvs.agrocomercial.componente.application.ComponentePrecoController;
import com.totvs.agrocomercial.componente.application.ComponentePrecoRequestDTO;
import com.totvs.agrocomercial.componente.application.ComponentePrecoResponseDTO;
import com.totvs.agrocomercial.componente.domain.*;
import com.totvs.agrocomercial.componente.domain.utils.*;
import com.totvs.agrocomercial.exceptions.RecordNotFoundException;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.core.validation.ValidatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Transactional
@SpringBootTest()
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DisplayName("Testes integrados da ComponentePreco")
public class ComponentePrecoControllerTest {

    private static final UUID idNaoExistente = UUID.fromString("4c7bd9ce-b5b6-11ea-b3de-0242ac130004");
    final Set<Finalidade> finalidades = new HashSet<>();
    final Set<TiposFrete> tiposFrete = new HashSet<>();
    final Set<Item> itens = new HashSet<>();
    final Set<ItemComponentePrecoDTO> idsComponentes = new HashSet<>();

    final UUID uidFinalidade = UUID.fromString("49abe010-0e29-11eb-adc1-0242ac120002");
    final UUID uidFrete = UUID.fromString("8a8127ca-0e2a-11eb-adc1-0242ac120002");
    final UUID uidItem = UUID.fromString("8a812216-0e2a-11eb-adc1-0242ac120002");

    @Autowired
    ComponentePrecoController controller;

    @Autowired
    ComponentePrecoRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void cleanup(){
        jdbcTemplate.update("TRUNCATE COMPONENTE_PRECO CASCADE");
        jdbcTemplate.update("TRUNCATE FINALIDADE CASCADE");
        jdbcTemplate.update("TRUNCATE ITEM CASCADE");
        jdbcTemplate.update("TRUNCATE TIPOS_FRETE CASCADE");
        jdbcTemplate.update("INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe010-0e29-11eb-adc1-0242ac120002', 'INDUSTRIALIZACAO' );"+
                                "INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe38a-0e29-11eb-adc1-0242ac120002', 'EXPORTACAO DIRETA');"+
                                "INSERT INTO FINALIDADE (ID,  DESCRICAO) VALUES('49abe484-0e29-11eb-adc1-0242ac120002', 'COMPRA MATERIA-PRIMA')");
        jdbcTemplate.update("INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a812216-0e2a-11eb-adc1-0242ac120002', 'MILHO' );"+
                                "INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a81248c-0e2a-11eb-adc1-0242ac120002', 'SOJA' );"+
                                "INSERT INTO ITEM (ID,  DESCRICAO) VALUES('8a8125ea-0e2a-11eb-adc1-0242ac120002', 'TRIGO' );");
        jdbcTemplate.update("INSERT INTO TIPOS_FRETE (ID, DESCRICAO) VALUES('8a8127ca-0e2a-11eb-adc1-0242ac120002', 'CIF' );"+
                                "INSERT INTO TIPOS_FRETE (ID,  DESCRICAO) VALUES('8a81248c-0e2a-11eb-adc1-0242ac120002', 'SOJA' );");

        UUID uidFinalidade = UUID.fromString("49abe010-0e29-11eb-adc1-0242ac120002");
        UUID uidFrete = UUID.fromString("8a8127ca-0e2a-11eb-adc1-0242ac120002");
        UUID uidItem = UUID.fromString("8a812216-0e2a-11eb-adc1-0242ac120002");

        finalidades.add(new Finalidade(uidFinalidade,"INDUSTRIALIZACAO"));
        tiposFrete.add(new TiposFrete(uidFrete,"CIF"));
        itens.add(new Item(uidItem,"MILHO"));
    }



    @Test
    @DisplayName("Um componente de preço deve existir no banco de dados após ser criado.")
    public void criar(){

        ComponentePrecoRequestDTO dto = new ComponentePrecoRequestDTO("CBOT",
                "CBOT",
                EnumUnidadeMedida.KG,
                EnumMoeda.REAL,
                "EXTERNO",
                EnumTipo.PRECO,
                EnumTabelaPreco.CBOT,
                EnumAplicacao.COMPRA,
                true,
                true,
                finalidades,
                tiposFrete,
                itens,
                idsComponentes
        );

        ComponentePrecoResponseDTO NovoComponentePreco = controller.create(dto).getBody();

        ComponentePreco componentePreco = repository.findById(NovoComponentePreco.getId()).orElseThrow();

        Assertions.assertNotNull(componentePreco);
        Assertions.assertNotNull(componentePreco.getFinalidades());
        Assertions.assertNotNull(componentePreco.getItens());
        Assertions.assertNotNull(componentePreco.getId());
        Assertions.assertEquals(EnumUnidadeMedida.KG, componentePreco.getUnidadeMedida());
        Assertions.assertEquals(EnumMoeda.REAL, componentePreco.getMoeda());
        Assertions.assertEquals("EXTERNO", componentePreco.getCodigoExterno());
        Assertions.assertEquals(EnumTipo.PRECO, componentePreco.getTipo());
        Assertions.assertEquals(EnumTabelaPreco.CBOT, componentePreco.getTabelaPreco());
        Assertions.assertEquals(EnumAplicacao.COMPRA, componentePreco.getAplicacao());
        Assertions.assertTrue(componentePreco.isAtivo());
        Assertions.assertTrue(componentePreco.isHedge());
    }

    @Test
    @DisplayName("Após criar uma Unidade Administrativa, deve ser possível buscá-lo pelo ID")
    public void buscarPorId() {


        ComponentePrecoResponseDTO novoComponentePreco = controller.create(new ComponentePrecoRequestDTO("CBOT",
                        "CBOT - COMPONENTE",
                        EnumUnidadeMedida.KG,
                        EnumMoeda.REAL,
                        "EXTERNO",
                        EnumTipo.PRECO,
                        EnumTabelaPreco.CBOT,
                        EnumAplicacao.COMPRA,
                        true,
                        true,
                        finalidades,
                        tiposFrete,
                        itens,
                        idsComponentes
                )
        ).getBody();

        ComponentePrecoResponseDTO componentePreco = controller.findBy(novoComponentePreco.getId().toString()).getBody();

        Assertions.assertNotNull(componentePreco);
        Assertions.assertNotNull(componentePreco.getFinalidades());
        Assertions.assertNotNull(componentePreco.getItens());
        Assertions.assertNotNull(componentePreco.getId());
        Assertions.assertEquals(EnumUnidadeMedida.KG, componentePreco.getUnidadeMedida());
        Assertions.assertEquals(EnumMoeda.REAL, componentePreco.getMoeda());
        Assertions.assertEquals("EXTERNO", componentePreco.getCodigoExterno());
        Assertions.assertEquals(EnumTipo.PRECO, componentePreco.getTipo());
        Assertions.assertEquals(EnumTabelaPreco.CBOT, componentePreco.getTabelaPreco());
        Assertions.assertEquals(EnumAplicacao.COMPRA, componentePreco.getAplicacao());
        Assertions.assertTrue(componentePreco.isAtivo());
        Assertions.assertTrue(componentePreco.isHedge());
    }

    @Test
    @DisplayName("Ao buscar um Componente de Preço com um ID não existente, deverá lançar a exceção RecordNotFoundException")
    public void buscarPorIdNaoExistente() {
        Assertions.assertThrows(RecordNotFoundException.class, () -> controller.findBy(idNaoExistente.toString()));
    }

    @Test
    @DisplayName("Buscar Componente utilizando um filtro com parte da descrição")
    public void buscarComFiltro() {
        Set<Finalidade> finalidades1 = new HashSet<>();
        Set<TiposFrete> tiposFrete1 = new HashSet<>();
        Set<Item> itens1 = new HashSet<>();

        Set<Finalidade> finalidades2 = new HashSet<>();
        Set<TiposFrete> tiposFrete2 = new HashSet<>();
        Set<Item> itens2 = new HashSet<>();

        Set<ItemComponentePrecoDTO> idsComponentes = new HashSet<>();

        finalidades1.add(new Finalidade(uidFinalidade,"INDUSTRIALIZACAO"));
        tiposFrete1.add(new TiposFrete(uidFrete,"CIF"));
        itens1.add(new Item(uidItem,"MILHO"));

        finalidades2.add(new Finalidade(uidFinalidade,"INDUSTRIALIZACAO"));
        tiposFrete2.add(new TiposFrete(uidFrete,"CIF"));
        itens2.add(new Item(uidItem,"MILHO"));

        controller.create(new ComponentePrecoRequestDTO("CBOT",
                "CBOT - COMPONENTE",
                EnumUnidadeMedida.KG,
                EnumMoeda.REAL,
                "EXTERNO",
                EnumTipo.PRECO,
                EnumTabelaPreco.CBOT,
                EnumAplicacao.COMPRA,
                true,
                true,
                finalidades1,
                tiposFrete1,
                itens1,
                idsComponentes
            )
        );

        controller.create(new ComponentePrecoRequestDTO("BASIS",
                "BASIS - COMPONENTE",
                EnumUnidadeMedida.KG,
                EnumMoeda.REAL,
                "EXTERNO",
                EnumTipo.PRECO,
                EnumTabelaPreco.CBOT,
                EnumAplicacao.COMPRA,
                true,
                true,
                finalidades2,
                tiposFrete2,
                itens2,
                idsComponentes
            )
        );

        Collection<ComponentePrecoResponseDTO> resultadoConsulta = controller.findAll(new ApiFieldRequest(), new ApiPageRequest(), new ApiSortRequest(), "COMP").getItems();
        Assertions.assertEquals(2, resultadoConsulta.size());

    }

    @Test
    @DisplayName("Após alterar componente de preço, os valores devem ter sido modificados no banco de dados")
    public void alterar() {

        finalidades.add(new Finalidade(uidFinalidade,"INDUSTRIALIZACAO"));
        tiposFrete.add(new TiposFrete(uidFrete,"CIF"));
        itens.add(new Item(uidItem,"MILHO"));
        ComponentePreco novoComponentePreco = repository.save(new ComponentePreco("CBOT",
                "CBOT - COMPONENTE",
                "EXTERNO",
                EnumUnidadeMedida.KG,
                EnumMoeda.REAL,
                EnumTipo.PRECO,
                EnumTabelaPreco.CBOT,
                EnumAplicacao.COMPRA,
                true,
                true,
                finalidades,
                tiposFrete,
                itens,
                idsComponentes
            )
        );

        ComponentePrecoRequestDTO dtoAlterado = new ComponentePrecoRequestDTO("CBOT",
                "CBOT ALTERADO",
                EnumUnidadeMedida.KG,
                EnumMoeda.REAL,
                "EXTERNO",
                EnumTipo.PRECO,
                EnumTabelaPreco.CBOT,
                EnumAplicacao.COMPRA,
                true,
                true,
                finalidades,
                tiposFrete,
                itens,
                idsComponentes
        );

        controller.update(novoComponentePreco.getId().toString(), dtoAlterado);

        ComponentePreco componentePrecoAlterado = repository.findById(novoComponentePreco.getId()).orElseThrow();
        Assertions.assertNotNull(componentePrecoAlterado);
        Assertions.assertEquals("CBOT", componentePrecoAlterado.getCodigo());
        Assertions.assertEquals("CBOT ALTERADO", componentePrecoAlterado.getDescricao());
    }

    @Test
    @DisplayName("Após remover um Componente de Preço, ele não deve mais existir no banco de dados")
    public void excluir() {

        finalidades.add(new Finalidade(uidFinalidade,"INDUSTRIALIZACAO"));
        tiposFrete.add(new TiposFrete(uidFrete,"CIF"));
        itens.add(new Item(uidItem,"MILHO"));
        ComponentePreco novoComponentePreco = repository.save(new ComponentePreco("CBOT",
                        "CBOT - COMPONENTE",
                        "EXTERNO",
                        EnumUnidadeMedida.KG,
                        EnumMoeda.REAL,
                        EnumTipo.PRECO,
                        EnumTabelaPreco.CBOT,
                        EnumAplicacao.COMPRA,
                        true,
                        true,
                        finalidades,
                        tiposFrete,
                        itens,
                        idsComponentes
                )
        );

        controller.delete(novoComponentePreco.getId().toString());
        Assertions.assertFalse(repository.existsById(novoComponentePreco.getId()));
    }
}
