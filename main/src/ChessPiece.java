public abstract class ChessPiece {
    protected boolean isWhite;

    public ChessPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    // 이동 규칙 검사용 추상 메서드
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol);
}

// --- 아래는 각 기물 클래스들 --- 
class Pawn extends ChessPiece {
    public Pawn(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int sr, int sc, int er, int ec) { 
        int rowDiff = Math.abs(sr - er);
        return (rowDiff == 1); 
    } 
}

class Rook extends ChessPiece {
    public Rook(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int sr, int sc, int er, int ec) {
        return (sr == er || sc == ec); // 상하좌우 이동
    }
}

class Knight extends ChessPiece {
    public Knight(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int sr, int sc, int er, int ec) {
        int rowDiff = Math.abs(sr - er);
        int colDiff = Math.abs(sc - ec);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2); // L자 이동
    }
}

class Bishop extends ChessPiece {
    public Bishop(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int sr, int sc, int er, int ec) {
        int rowDiff = Math.abs(sr - er);
        int colDiff = Math.abs(sc - ec); 
        return (rowDiff == colDiff); // 대각선 이동
    } 
}

class Queen extends ChessPiece {
    public Queen(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int sr, int sc, int er, int ec) { 
        int rowDiff = Math.abs(sr - er);
        int colDiff = Math.abs(sc - ec);
        return (sr == er || sc == ec || rowDiff == colDiff); // 상화좌우 + 대각선 이동
    }
}

class King extends ChessPiece {
    public King(boolean isWhite) { super(isWhite); }
    @Override
    public boolean isValidMove(int sr, int sc, int er, int ec) { 
        int rowDiff = Math.abs(sr - er);
        int colDiff = Math.abs(sc - ec);
        return (rowDiff == 1 || colDiff == 1); // 1칸 이동
    }
}