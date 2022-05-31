package pe.com.bank.yanki.transaction.service;

import java.util.Date;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.com.bank.yanki.transaction.document.YankiTransacionDocument;
import pe.com.bank.yanki.transaction.dto.WalletOperationDTO;
import pe.com.bank.yanki.transaction.repository.YankiTransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@Service
public class YankiTransactionServiceImpl  implements YankiTransactionService{
	
	
	YankiTransactionRepository yankiTransactionRepository;
	
	public Flux<YankiTransacionDocument> getAllYankiTransaction(){
		return yankiTransactionRepository.findAll();
	}
	
	public Mono<YankiTransacionDocument> getYankiTransactionById(String YankiTransacionId){
		return yankiTransactionRepository.findById(YankiTransacionId);
	}
	
	public Mono<YankiTransacionDocument> saveYankiTransaction(YankiTransacionDocument yankiTransacionDocument){
		return yankiTransactionRepository.save(yankiTransacionDocument);
	}
	
	public Mono<YankiTransacionDocument> updateYankiTransactionById(YankiTransacionDocument UpdateYankiTransacionDocument,String YankiTransacionId){
		return yankiTransactionRepository.findById(YankiTransacionId).flatMap( yankiTransaction -> {
			
			yankiTransaction.setAmount(UpdateYankiTransacionDocument.getAmount() !=null ? UpdateYankiTransacionDocument.getAmount():yankiTransaction.getAmount());
			yankiTransaction.setDateTransaction(UpdateYankiTransacionDocument.getDateTransaction() !=null ? UpdateYankiTransacionDocument.getDateTransaction():yankiTransaction.getDateTransaction());
			yankiTransaction.setSourceWalletId(UpdateYankiTransacionDocument.getSourceWalletId() !=null ? UpdateYankiTransacionDocument.getSourceWalletId():yankiTransaction.getSourceWalletId());
			yankiTransaction.setDestinationWalletId(UpdateYankiTransacionDocument.getDestinationWalletId() !=null ? UpdateYankiTransacionDocument.getDestinationWalletId():yankiTransaction.getDestinationWalletId());
			return yankiTransactionRepository.save(yankiTransaction);

		});
	}
	
	public Mono<Void> deleteYankiTransactionById(String YankiTransacionId){
		return yankiTransactionRepository.deleteById(YankiTransacionId);
	}
	
	 @Bean
	 Consumer<WalletOperationDTO> walletTransaction() {
	    return walletOperationDTO -> {
	    	
	  	    yankiTransactionRepository.save(new YankiTransacionDocument(null,walletOperationDTO.getAmount(),new Date(),walletOperationDTO.getSourceWalletId(),
	        		   walletOperationDTO.getDestinationWalletId())).subscribe();	           
	    };
	  }

}
