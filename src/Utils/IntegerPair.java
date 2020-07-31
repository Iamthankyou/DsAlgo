package Utils;

public class IntegerPair implements Comparable<IntegerPair>{
	private Integer _first,_second;
	
	public IntegerPair(Integer first, Integer second) {
		this._first = first;
		this._second = second;
	}
	
	@Override
	public int compareTo(IntegerPair i) {
		if (first().equals(i.first())) {
			return _first - i.first();
		}
		else {
			return _second - i.second();
		}
	}
	
	public Integer first() {
		return _first;
	}
	
	public Integer second() {
		return _second;
	}
	
	public void setFirst(Integer first) {
		this._first = first;
	}
	
	public void setSecond(Integer second) {
		this._second = second;
	}
}
