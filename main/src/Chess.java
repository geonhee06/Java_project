import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess extends JFrame {

    ChessButton[][] chessBoard = new ChessButton[8][8]; // 체스판
    ChessPiece[][] logicBoard = new ChessPiece[8][8]; // 기물 로직
    ChessButton selectedPiece = null;

    boolean isGameOver = false; // 게임 종료 변수
    boolean isWhiteTurn = true; // 턴 제어 변수

    Chess() {
        setTitle("ChessGame - 백(White)의 차례입니다"); // 턴제 알림
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
        setSize(800, 800);

        ActionListener pieceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isGameOver) return; 

                ChessButton clickedPiece = (ChessButton) e.getSource();

                // 기물 선택
                if (selectedPiece == null) {
                    ChessPiece clickedLogicPiece = logicBoard[clickedPiece.row][clickedPiece.col];

                    // 자신의 턴에만 기물 선택 가능
                    if (clickedLogicPiece != null && clickedLogicPiece.isWhite() == isWhiteTurn) {
                        selectedPiece = clickedPiece;
                    } else if (clickedLogicPiece != null) {
                        System.out.println("상대방의 기물은 움직일 수 없습니다!");
                    }
                } 
                // 기물 제어
                else {
                    int startRow = selectedPiece.row;
                    int startCol = selectedPiece.col;
                    int endRow = clickedPiece.row;
                    int endCol = clickedPiece.col;

                    ChessPiece pieceToMove = logicBoard[startRow][startCol];
                    ChessPiece targetCell = logicBoard[endRow][endCol];

                    // 기물 이동 시 아군이 없는 칸으로 이동 가능
                    boolean isFriend = (targetCell != null) && (targetCell.isWhite() == pieceToMove.isWhite());

                    if (!isFriend && pieceToMove.isValidMove(startRow, startCol, endRow, endCol)) {
                        
                        // 캉이 죽으면 게임 종료
                        if (targetCell instanceof King) {
                            String winner = pieceToMove.isWhite() ? "백(White)" : "흑(Black)";
                            JOptionPane.showMessageDialog(null, "게임 종료! " + winner + "가 승리했습니다!");
                            isGameOver = true; 
                        }

                        clickedPiece.setIcon(selectedPiece.getIcon());
                        selectedPiece.setIcon(null);

                        // 데이터 보드 갱신
                        logicBoard[endRow][endCol] = pieceToMove;
                        logicBoard[startRow][startCol] = null;

                        // 턴제
                        if (!isGameOver) {
                            isWhiteTurn = !isWhiteTurn; // 턴 뒤집기
                            //턴제 알림
                            if (isWhiteTurn) {
                                setTitle("ChessGame - 백(White)의 차례입니다");
                            } else {
                                setTitle("ChessGame - 흑(Black)의 차례입니다");
                            }
                        }

                    } else {
                        System.out.println("잘못된 이동입니다!");
                    }

                    // 선택 초기화
                    restoreBoardColors(); 
                    selectedPiece = null;
                }
            }
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new ChessButton(i, j);
                chessBoard[i][j].addActionListener(pieceListener);
                add(chessBoard[i][j]);
            }
        }
        restoreBoardColors(); // 색상 칠하기

        // 기물 이미지
        ImageIcon wcpp = new ImageIcon("./main/src/chessPieceImage/wcp_p_image.png");
        ImageIcon bcpp = new ImageIcon("./main/src/chessPieceImage/bcp_p_image.png");
        ImageIcon wcpKing = new ImageIcon("./main/src/chessPieceImage/wcp_King_image.png");
        ImageIcon bcpKing = new ImageIcon("./main/src/chessPieceImage/bcp_King_image.png");
        ImageIcon wcpQ = new ImageIcon("./main/src/chessPieceImage/wcp_Q_image.png");
        ImageIcon bcpQ = new ImageIcon("./main/src/chessPieceImage/bcp_Q_image.png");
        ImageIcon wcpb = new ImageIcon("./main/src/chessPieceImage/wcp_b_image.png");
        ImageIcon bcpb = new ImageIcon("./main/src/chessPieceImage/bcp_b_image.png");
        ImageIcon wcpk = new ImageIcon("./main/src/chessPieceImage/wcp_k_image.png");
        ImageIcon bcpk = new ImageIcon("./main/src/chessPieceImage/bcp_k_image.png");
        ImageIcon wcpr = new ImageIcon("./main/src/chessPieceImage/wcp_r_image.png");
        ImageIcon bcpr = new ImageIcon("./main/src/chessPieceImage/bcp_r_image.png");

        // 기물 크기 조절
        Image whitePawn = wcpp.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image blackPawn = bcpp.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image whiteKing = wcpKing.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image blackKing = bcpKing.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image whiteQueen = wcpQ.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image blackQueen = bcpQ.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image whiteBishop = wcpb.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image blackBishop = bcpb.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image whiteKnight = wcpk.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image blackKnight = bcpk.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image whiteRook = wcpr.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image blackRook = bcpr.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        
        // 폰
        for(int i = 0; i < 8; i++){
            chessBoard[6][i].setIcon(new ImageIcon(whitePawn)); logicBoard[6][i] = new Pawn(true);
            chessBoard[1][i].setIcon(new ImageIcon(blackPawn)); logicBoard[1][i] = new Pawn(false);
        }

        // 킹
        chessBoard[7][4].setIcon(new ImageIcon(whiteKing)); logicBoard[7][4] = new King(true);
        chessBoard[0][4].setIcon(new ImageIcon(blackKing)); logicBoard[0][4] = new King(false);

        // 퀸
        chessBoard[7][3].setIcon(new ImageIcon(whiteQueen)); logicBoard[7][3] = new Queen(true);
        chessBoard[0][3].setIcon(new ImageIcon(blackQueen)); logicBoard[0][3] = new Queen(false);

        // 비숍
        chessBoard[7][2].setIcon(new ImageIcon(whiteBishop)); logicBoard[7][2] = new Bishop(true);
        chessBoard[7][5].setIcon(new ImageIcon(whiteBishop)); logicBoard[7][5] = new Bishop(true);
        chessBoard[0][2].setIcon(new ImageIcon(blackBishop)); logicBoard[0][2] = new Bishop(false);
        chessBoard[0][5].setIcon(new ImageIcon(blackBishop)); logicBoard[0][5] = new Bishop(false);

        // 나이트
        chessBoard[7][1].setIcon(new ImageIcon(whiteKnight)); logicBoard[7][1] = new Knight(true);
        chessBoard[7][6].setIcon(new ImageIcon(whiteKnight)); logicBoard[7][6] = new Knight(true);
        chessBoard[0][1].setIcon(new ImageIcon(blackKnight)); logicBoard[0][1] = new Knight(false);
        chessBoard[0][6].setIcon(new ImageIcon(blackKnight)); logicBoard[0][6] = new Knight(false);

        // 룩
        chessBoard[7][0].setIcon(new ImageIcon(whiteRook)); logicBoard[7][0] = new Rook(true);
        chessBoard[7][7].setIcon(new ImageIcon(whiteRook)); logicBoard[7][7] = new Rook(true);
        chessBoard[0][0].setIcon(new ImageIcon(blackRook)); logicBoard[0][0] = new Rook(false);
        chessBoard[0][7].setIcon(new ImageIcon(blackRook)); logicBoard[0][7] = new Rook(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 체스판 색상 복구
    private void restoreBoardColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    chessBoard[i][j].setBackground(Color.WHITE);
                } else {
                    chessBoard[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Chess();
    }
}