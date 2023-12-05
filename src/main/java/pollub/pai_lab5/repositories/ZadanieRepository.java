package pollub.pai_lab5.repositories;

import org.springframework.data.repository.CrudRepository;
import pollub.pai_lab5.entities.Zadanie;

import java.util.List;

public interface ZadanieRepository extends CrudRepository<Zadanie, Long> {

    List<Zadanie> findAllByWykonane (boolean wykonane);

    List<Zadanie> findByKosztLessThan(double koszt);

    List<Zadanie> findByKosztBetween(double min, double max);
}
