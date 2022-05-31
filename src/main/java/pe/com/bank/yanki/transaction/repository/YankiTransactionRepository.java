package pe.com.bank.yanki.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.yanki.transaction.document.YankiTransacionDocument;

@Repository
public interface YankiTransactionRepository extends ReactiveMongoRepository<YankiTransacionDocument, String>{

}
