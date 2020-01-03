package practies;

import java.util.Iterator;

import examples.Enums;

class Mail2{
	//The NO's lower the probalility of random selection;
	enum GeneralDelivery{YES,NO1,NO2,NO3,NO4,NO5};
	enum Scannability{UNSCANNABLE,YES1,YES2};
	enum Readability{ILLEGIBLE,YES1,YES2,YES3,YES4}
	enum ForwardAddress{YES,NO1,NO2,NO3,NO4,NO5};
	enum Address{INCORRECT,OK1,OK2,OK3,Ok4,OK5}
	enum ReturnAddress{MISSING,OK1,OK2,OK3,OK4,Ok5}
	GeneralDelivery generalDelivery;
	Scannability scannability;
	Readability readability;
	ForwardAddress forwardAddress;
	Address address;
	ReturnAddress returnAddress;
	static long counter = 0;
	long id = counter ++;
	public String toString() {return "Mail" + id;}
	public String details() {
		return toString() +
				", General Delivery: " + generalDelivery +
				", Address Scannability: " + scannability +
				", Address Readability: " + readability +
				", ForwardAddress: " + forwardAddress +
				", Address Address:" + address +
				", Return address:" + returnAddress;
	}
	//Generate test Mail:
	public static Mail2 randomMail() {
		Mail2 m = new Mail2();
		m.generalDelivery = Enums.random(GeneralDelivery.class);
		m.scannability = Enums.random(Scannability.class);
		m.readability = Enums.random(Readability.class);
		m.forwardAddress = Enums.random(ForwardAddress.class);
		m.address = Enums.random(Address.class);
		m.returnAddress = Enums.random(ReturnAddress.class);
		return m;
	}
	public static Iterable<Mail2> generator(final int count){
		return new Iterable<Mail2>() {
			int n = count;
			@Override
			public Iterator<Mail2> iterator() {
				return new Iterator<Mail2>() {
					@Override
					public boolean hasNext() {
						return n-- > 0;
					}
					@Override
					public Mail2 next() {
						return randomMail();
					}
					public void remove() {//not implemented
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}
public class PostOffice8 {
	enum MailHandler{
		GENERAL_DELIVERY{
			boolean handle(Mail2 m) {
				switch(m.generalDelivery) {
				case YES: 
					System.out.println("Using general delivery for " + m); 
					return true;
				default: return false;
				}
			}
		},
		MACHINE_SCAN{
			boolean handle(Mail2 m) {
				switch(m.scannability) {
				case UNSCANNABLE : return false;
				default:
					switch(m.forwardAddress) {
					case YES: 
						System.out.println("Forwarding " + m + " automatically");
						return true;
					default: 
						switch(m.address) {
						case INCORRECT:
							return false;
						default:
							System.out.println("Delivery " + m + " automatically");
							return true;
						}
					}
				}
			}
		},
		FORWARDING{
			boolean handle(Mail2 m) {
				switch(m.forwardAddress) {
				case YES:
					System.out.println("Forwarding " + m);
					return true;
				default:
					return false;
				}
			}
		},
		VISUAL_INSPECTION{
			boolean handle(Mail2 m) {
				switch(m.readability) {
				case ILLEGIBLE:
					return false;
				default:
					switch(m.address) {
					case INCORRECT:
						return false;
					default:
						System.out.println("Delivering " + m + " normally");
						return true;
					}
				}
			}
		},
		RETRUN_TO_SENDER{
			boolean handle(Mail2 m) {
				switch(m.returnAddress) {
				case MISSING: 
					return false;
				default:
					System.out.println("Returning " + m + " to sender");
					return true;
				}
			}
		};
		abstract boolean handle(Mail2 m);
	}
	static void handle(Mail2 m) {
		for(MailHandler handler : MailHandler.values()) {
			if(handler.handle(m))
				return;
		}
		System.out.println(m + " is a dead letter");
	}
	public static void main(String[] args) {
		for(Mail2 mai : Mail2.generator(20)) {
			System.out.println(mai.details());
			handle(mai);
			System.out.println("****************");
		}
	}
}
