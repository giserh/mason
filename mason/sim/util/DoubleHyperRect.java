package sim.util;

public class DoubleHyperRect implements Comparable<DoubleHyperRect> {
	public int nd, id;
	public DoublePoint ul, br;

	public DoubleHyperRect(int id, DoublePoint ul, DoublePoint br) {
		if (ul.nd != br.nd)
			throw new IllegalArgumentException("Number of dimensions must be the same. Got " + ul.nd + " and " + br.nd);

		this.nd = ul.nd;

		for (int i = 0; i < nd; i++)
			if (br.c[i] < ul.c[i])
				throw new IllegalArgumentException("All p2's components should be greater than or equal to p1's corresponding one");

		this.ul = ul;
		this.br = br;
		this.id = id;
	}

	public boolean isOverlap(DoubleHyperRect that) {
		if (this.nd != that.nd)
			throw new IllegalArgumentException("Number of dimensions must be the same. Got " + this.nd + " and " + that.nd);
		//TODO
		return false;
	}

	public int[] getRelPos(DoubleHyperRect that) {
		if (this.nd != that.nd)
			throw new IllegalArgumentException("Number of dimensions must be the same. Got " + this.nd + " and " + that.nd);
		//TODO
		return new int[] {};
	}

	public Segment getSegment(int dim) {
		if (dim < 0 || dim >= nd)
			throw new IllegalArgumentException("Illegal dimension: " + dim);
		return new Segment(ul.c[dim], br.c[dim]);
	}

	public double getSize() {
		return br.rectSize(ul);
	}

	public DoubleHyperRect reduceDim(int dim) {
		return new DoubleHyperRect(id, ul.reduceDim(dim), br.reduceDim(dim));
	}

	// Sort the rectangles based on its upper left corner first and then bottom-right corner and then id
	@Override
	public int compareTo(DoubleHyperRect that) {
		int ret;

		if ((ret = this.ul.compareTo(that.ul)) != 0)
			return ret;
		if ((ret = this.br.compareTo(that.br)) != 0)
			return ret;

		return this.id - that.id;
	}

	public String toString() {
		return String.format("%s<%d, %s, %s>", this.getClass().getSimpleName(), id, ul.toString(), br.toString());
	}
}