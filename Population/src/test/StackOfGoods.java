package test;

import pop.Good;

public class StackOfGoods {
	private Good good;
	private int amount;

	public StackOfGoods(Good good, int amount) {
		super();
		this.good = good;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public StackOfGoods setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public Good getGood() {
		return good;
	}

	public StackOfGoods setGood(Good good) {
		this.good = good;
		return this;
	}

}
