public class Account {
	double balance;
	double interestRate;
	
	void deposit(double innbet) {
		if (innbet >= 0) {
			balance +=innbet;
		}}
	void addInterest() {
		double tempRente = balance*interestRate/100;
		balance+= tempRente;
	}
	double getBalance() {
		return balance;
	}
	double getInterestRate() {
		return interestRate;
	}
	void setInterestRate(double nyRente) {
		interestRate = nyRente;
	}
	public String toString() {
		return "Balance: "+balance+"\nRate: "+interestRate+"";
	}
	public static void main(String[] args) {
		Account a = new Account();
		a.setInterestRate(5);
		a.deposit(1000);
		a.addInterest();
		a.getBalance();
		System.out.println(a);
		System.out.println();
	}
}