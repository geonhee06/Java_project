import javax.swing.JButton;


public class ChessButton extends JButton {
    public int row;
    public int col;

    public ChessButton(int row, int col) {
        this.row = row;
        this.col = col;
        // 버튼 배경색이 잘 보이도록 투명도 설정 (Mac 등 호환용)
        setOpaque(true);
        setBorderPainted(false);
    }
}