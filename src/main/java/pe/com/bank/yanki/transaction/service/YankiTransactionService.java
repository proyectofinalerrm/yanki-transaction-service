package pe.com.bank.yanki.transaction.service;

import pe.com.bank.yanki.transaction.document.YankiTransacionDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiTransactionService {
	
	
	public Flux<YankiTransacionDocument> getAllYankiTransaction();
	public Mono<YankiTransacionDocument> getYankiTransactionById(String YankiTransacionId);
	public Mono<YankiTransacionDocument> saveYankiTransaction(YankiTransacionDocument yankiTransacionDocument);
	public Mono<YankiTransacionDocument> updateYankiTransactionById(YankiTransacionDocument yankiTransacionDocument,String YankiTransacionId);
	public Mono<Void> deleteYankiTransactionById(String YankiTransacionId);

}
