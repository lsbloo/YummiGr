package com.com.yummigr.graphics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.yummigr.services.ContactService;
import com.com.yummigr.services.MessengerService;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.LoggerSender;
import com.com.yummigr.models.Messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author osvaldoairon
 *
 *
all the interactions of the messenger connector and creation of graphs
specialized in the information of sending of sms and emails.
 */



@Service
public class GeneratorFreeChartService {
	
	
	
	private final MessengerService messengerService;
	
	private final ContactService contactService;
	
	
	@Autowired
	public GeneratorFreeChartService(MessengerService msn , ContactService cc) {
		this.messengerService=msn;
		this.contactService=cc;
	
	}
	
	
	
	/**
	 * retorna a quantidade de emails enviados para um determinado contato 
	 * // quantos emails eu enviei para um determinado contato em um mes?
	 * pelo periodo de um mês filtro 01;
	 * @param cc
	 * @return
	 */
	public List<HashMap<LoggerSender,Integer>> getAttrInformationContactTrackerEmailsMnth(List<Contacts> cc , String month){
		Integer cont = 0 ;
		List<HashMap<LoggerSender,Integer>> maps = new ArrayList<HashMap<LoggerSender,Integer>>();
		for(Contacts c : cc ){
		
		
		List<LoggerSender> s = getLoggerSenderContact(c.getId());
		maps.add(this.contactService.getLoggerEmailByDataMonth(s, month));
		
		}
		return maps;
		
	}
	public List<HashMap<LoggerSender,Integer>> getAttrInformationContactTrackerEmailsYear(List<Contacts> cc , String year){
		Integer cont = 0 ;
		List<HashMap<LoggerSender,Integer>> maps = new ArrayList<HashMap<LoggerSender,Integer>>();
		for(Contacts c : cc ){
		
		
		List<LoggerSender> s = getLoggerSenderContact(c.getId());
		maps.add(this.contactService.getLoggerEmailByDataYear(s, year));
		
		}
		return maps;
		
	}
	public List<HashMap<LoggerSender,Integer>> getAttrInformationContactTrackerEmailsFull(List<Contacts> cc , String month, String day, String year){
		Integer cont = 0 ;
		List<HashMap<LoggerSender,Integer>> maps = new ArrayList<HashMap<LoggerSender,Integer>>();
		for(Contacts c : cc ){
		
		
		List<LoggerSender> s = getLoggerSenderContact(c.getId());
		maps.add(this.contactService.getLoggerEmailByDataFull(s, month, day, year));
		
		}
		return maps;
		
	}
	
	public List<Contacts> getLoggerRelated(List<HashMap<LoggerSender,Integer>> maps){
		List<Contacts> cc = new ArrayList<Contacts>();
		for(HashMap<LoggerSender,Integer> m : maps) {
			for(LoggerSender s : m.keySet()) {
				cc.add(this.contactService.getContactRelatedDirectLogger(s));
			}
		}
		
		return checkContacts(cc);
	}
	
	
	public List<Contacts> checkContacts(List<Contacts> list_contacts){
		 List<Contacts> check = new ArrayList<Contacts>();
		 List<Long> list_ids_o = new ArrayList<Long>();
		  int cont = 0;
         int mec[] = new int[list_contacts.size()];
        
        for (Contacts x : list_contacts) {
       	 if(x!=null)mec[cont] = (int) x.getId();cont++;
        }
        
        int[] saida = new int[mec.length];
        int qnt=0;
        for(int i = 0 ; i < mec.length ; i++) {
       	boolean verific = false;
       	
       	for(int j = 0 ; j < qnt ; j ++) {
       		if(saida[j] == mec[i]) {
       			verific = true;
       			break;
       		}
       	}
       	if(!verific) {
       		saida[qnt++] = mec[i];
       	}
        }
        for(int i = 0 ; i < saida.length ; i ++) {
       	 if(saida[i] != 0 ) {
       		 list_ids_o.add((long) saida[i]);
       	 }
        }
        for(Long x : list_ids_o) {
       	 check.add(this.contactService.getContactById((x)));
        
        }
        
        return check;
	}
	
	
	public LoggerSender getInformationLoggerByContact(Long logger_id){
		return this.contactService.getLoggerById(logger_id);
	}
	
	public List<LoggerSender> getLoggerSenderContact(Long contact_id){
		List<Long>  list_id_related = this.contactService.getIdsLoggerSenderRelatedContact(contact_id);
		List<LoggerSender> cc = new ArrayList<LoggerSender>();
		for(Long id : list_id_related) {
			cc.add(this.contactService.getLoggerById(id));
		}
		return cc;
	}


	public List<Contacts> getContactsByMessengerConnector(String identifier){
		Messenger u = this.messengerService.searchConnectorMessengerUser(identifier);
		return this.messengerService.getAllContactsForMessenger(u);
	}
	
}
