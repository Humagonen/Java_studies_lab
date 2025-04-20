package Test;

public class AsciiColors {

	public static void main(String[] args) {
		final int columns = 8; // Number of columns per row
		for (int i = 0; i < 256; i++) {
			// ANSI escape code for 256 colors: \033[38;5;{i}m for foreground
			System.out.printf("\033[38;5;%dm\033[1m\\033[38;5;%dm\033[0m ", i, i);
			// Break line after 'columns' colors
			if ((i + 1) % columns == 0) {
				System.out.println();
			}
		}
	}
}
