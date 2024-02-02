package Service;

import Model.Quarto;
import Repository.QuartoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class QuartoService {

    @Autowired
    QuartoRepository quartoRepository;

    @Transactional
    public Quarto create(Quarto quarto) {
        Quarto entity = new Quarto();
        entity = quartoRepository.save(entity);
        return new Quarto(entity);
    }

    @Transactional(readOnly = true)
    public List<Quarto> findAll() {
        List<Quarto> list = quartoRepository.findAll();
        return list.stream().map(quarto -> new Quarto(quarto)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Quarto findById(long id) {
        Optional<Quarto> optionalQuarto = quartoRepository.findById(id);
        return new Quarto();
    }

    @Transactional
    public Quarto update(Long id, Quarto quarto) {
        Quarto entity = quartoRepository.getOne(id);
        entity = quartoRepository.save(entity);
        return new Quarto(entity);
    }

    public void delete(long id) {
       quartoRepository.deleteById(id);
    }
}

