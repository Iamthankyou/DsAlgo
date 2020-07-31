package Utils;

public class IntegerTriple implements Comparable<IntegerTriple> {
	private Integer _first,_second,_third;
	
	public IntegerTriple(Integer f, Integer s, Integer t) {
		_first = f;
		_second = s;
		_third = t;
	}
	
	@Override
	public int compareTo(IntegerTriple i) {
		if (_first.equals(i.first())) {
			return _first-i.first();
		}
		else {
			return _second-i.second();
		}
	}
	
	public Integer first() {
		return _first;
	}
	
	public Integer second() {
		return _second;
	}
	
	public Integer third() {
		return _third;
	}
	
	public void setFirst(Integer f) {
		_first = f;
	}
	
	public void setSecond(Integer s) {
		_second = s;
	}
	
	public void setThird(Integer t) {
		_third = t;
	}
}
