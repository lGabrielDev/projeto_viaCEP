package br.com.lGabrielDev.projetoClientes.clientes;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import feign.Param;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    //queries
    @Query(value = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    public Optional<Cliente> findByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT c FROM Cliente c WHERE c.email = :email")
    public Optional<Cliente> findByEmail(@Param("email") String email);

    @Query(value = "SELECT c FROM Cliente c WHERE c.telefone = :telefone")
    public Optional<Cliente> findByTelefone(@Param("telefone") String telefone);

    @Query(value = "SELECT c FROM Cliente c ORDER BY c.id ASC")
    public List<Cliente> findAllOrderByIdAsc();

    @Query(value = "SELECT c FROM Cliente c WHERE c.enderecoCompleto.ddd = :ddd ORDER BY c.id ASC")
    public List<Cliente> findAllFilterByDdd(@Param("ddd") Integer ddd);
    
    @Query(value = "SELECT c FROM Cliente c WHERE c.enderecoCompleto.uf = :uf ORDER BY c.id ASC")
    public List<Cliente> findAllFilterByUf(@Param("uf") String uf);

    @Query(value = "SELECT c FROM Cliente c WHERE c.enderecoCompleto.ddd = :ddd AND c.enderecoCompleto.uf = :uf ORDER BY c.id ASC")
    public List<Cliente> findAllFilterByDddAndUf(@Param("ddd") Integer ddd, @Param("uf") String uf);
}