package bsuir.pechuro.entity;


public class Account {

    private int accountNumber;
    private double accountCredit;
    private int clientId;

    public Account(int accountNumber, double accountCredit, int clientId) {
        this.accountNumber = accountNumber;
        this.accountCredit = accountCredit;
        this.clientId = clientId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(double accountCredit) {
        this.accountCredit = accountCredit;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountNumber != account.accountNumber) return false;
        if (Double.compare(account.accountCredit, accountCredit) != 0) return false;
        return clientId == account.clientId;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountNumber;
        temp = Double.doubleToLongBits(accountCredit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + clientId;
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountCredit=" + accountCredit +
                ", clientId=" + clientId +
                '}';
    }

}
