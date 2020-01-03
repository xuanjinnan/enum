package practies;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import examples.Enums;

class Mail{
	//THe NO's lower the probability of random selection:
	public enum GeneralDelivery{YES,NO1,NO2,NO3,NO4,NO5}
	public enum Scannability{UNSCANNABLE,YES1,YES2,YES3,YES4}
	public enum Readability{ILLEGIBLE,YES1,YES2,YES3,YES4}
	public enum Address{INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
	public enum ReturnAddress{MISSING,OK1,OK2,OK3,OK4,OK5}
	GeneralDelivery generalDelivery;
	Scannability scannability;
	Readability readability;
	Address address;
	ReturnAddress returnAddress;
	static long counter = 0;
	long id = counter ++;
	public String toString(){
		return "Mail" + id;
	}
	public String deatils() {
		return toString() +
				", GeneralDelivery: " + generalDelivery +
				", Scannability: " + scannability +
				", Readability: " + readability +
				", Adress: " + address +
				", ReturnAddress: " + returnAddress;
	}
	public static Mail randomMail() {
		Mail m = new Mail();
		m.generalDelivery = Enums.random(GeneralDelivery.class);
		m.scannability = Enums.random(Scannability.class);
		m.readability = Enums.random(Readability.class);
		m.address = Enums.random(Address.class);
		m.returnAddress = Enums.random(ReturnAddress.class);
		return m;
	}
	public static Iterable<Mail> generator(final int count) {
		return new Iterable<Mail>() {
			int n = count;
			@Override
			public Iterator<Mail> iterator() {
				return new Iterator<Mail>() {

					@Override
					public boolean hasNext() {
						return n-- > 0;
					}

					@Override
					public Mail next() {
						return randomMail();
					}
					public void remove() {//Not implemented
						throw new UnsupportedOperationException();
					}
				};
			}};
	}
}
interface Handler{abstract boolean handle(Mail m);} //Command design pattern
public class PostOffice9 {
	enum MailHandler{GENERAL_DELIVERY,MACHINE_SCAN,VISUAL_INSPECTION,RETRUN_TO_SENDER}
	
	public static void handle(Mail m,EnumMap<MailHandler,Handler> em) {
		for(Map.Entry<MailHandler,Handler> e : em.entrySet()) {
			if(e.getValue().handle(m))
				return;
		}	
		System.out.println(m + "is a dead letter");
	}
	public static void main(String[] args) {
		EnumMap<MailHandler, Handler> em = new EnumMap<MailHandler,Handler>(MailHandler.class);
		em.put(MailHandler.GENERAL_DELIVERY, new Handler() {
			@Override
			public boolean handle(Mail m) {
				switch(m.generalDelivery) {
				case YES:
					System.out.println("Using general delivery for " + m);
					return true;
				default:
					return false;
				}
			}
		});
		em.put(MailHandler.MACHINE_SCAN, new Handler() {
			public boolean handle(Mail m) {
				switch(m.scannability) {
				case UNSCANNABLE:return false;
				default:
					switch(m.address) {
					case INCORRECT :
						return false;
					default:
						System.out.println("Delivering " + m + " automaticaly");
						return true;
					}
				}
			}
		});
		em.put(MailHandler.VISUAL_INSPECTION, new Handler() {
			public boolean handle(Mail m) {
				switch(m.readability) {
				case ILLEGIBLE:
					return false;
				default:
					switch(m.address) {
					case INCORRECT: return false;
					default:
						System.out.println("Delivering " + m + " normally");
						return true;
					}
				}
			}
		});
		em.put(MailHandler.RETRUN_TO_SENDER, new Handler() {
			public boolean handle(Mail m) {
				switch(m.returnAddress) {
				case MISSING: return false;
				default:
					System.out.println("Returning " + m + " to sender");
					return true;
				}
			}
		});
		for(Mail mail : Mail.generator(10)) {
			System.out.println(mail.deatils());
			handle(mail, em);
			System.out.println("******");
		}
	}
}
