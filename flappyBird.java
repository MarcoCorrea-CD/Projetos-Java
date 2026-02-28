import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class flappyBird extends JFrame {
    public flappyBird() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new flappyBird());
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = -13;
    private static final int PIPE_SPEED = -4;
    private static final int PIPE_WIDTH = 80;
    private static final int PIPE_GAP = 140;
    
    private Bird bird;
    private java.util.ArrayList<Pipe> pipes;
    private int score = 0;
    private boolean gameOver = false;
    private boolean gameStarted = false;
    private Timer timer;
    
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(135, 206, 250)); // Azul claro
        setFocusable(true);
        addKeyListener(this);
        
        bird = new Bird(WIDTH / 8, HEIGHT / 2);
        pipes = new java.util.ArrayList<>();
        
        timer = new Timer(30, this);
        timer.start();
        
        addPipes();
    }
    
    private void addPipes() {
        int gapY = (int) (Math.random() * (HEIGHT - PIPE_GAP - 100)) + 50;
        pipes.add(new Pipe(WIDTH, gapY, PIPE_WIDTH, PIPE_GAP));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted && !gameOver) {
            bird.update();
            
            // Atualizar canos
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.update();
                
                // Verificar colisão
                if (bird.collidesWith(pipe)) {
                    gameOver = true;
                }
                
                // Remover cano que saiu da tela
                if (pipe.getX() < -PIPE_WIDTH) {
                    pipes.remove(i);
                    score++;
                }
            }
            
            // Adicionar novos canos
            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < WIDTH - 220) {
                addPipes();
            }
            
            // Verificar colisão com o chão e teto
            if (bird.getY() <= 0 || bird.getY() + bird.getHeight() >= HEIGHT) {
                gameOver = true;
            }
        }
        
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenhar pássaro
        bird.draw(g2d);
        
        // Desenhar canos
        g2d.setColor(new Color(34, 139, 34)); // Verde escuro
        for (Pipe pipe : pipes) {
            pipe.draw(g2d);
        }
        
        // Desenhar pontuação
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Score: " + score, 20, 40);
        
        // Desenhar mensagens
        if (!gameStarted) {
            g2d.setColor(new Color(0, 0, 0, 200));
            g2d.fillRect(0, 0, WIDTH, HEIGHT);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 32));
            g2d.drawString("Flappy Bird", WIDTH / 4, HEIGHT / 3);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.drawString("Pressione ESPAÇO para começar", WIDTH / 8, HEIGHT / 2);
        } else if (gameOver) {
            g2d.setColor(new Color(0, 0, 0, 200));
            g2d.fillRect(0, 0, WIDTH, HEIGHT);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 32));
            g2d.drawString("Game Over!", WIDTH / 5, HEIGHT / 2 - 20);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.drawString("Score: " + score, WIDTH / 3, HEIGHT / 2 + 30);
            g2d.drawString("Pressione ESPAÇO para jogar novamente", WIDTH / 10, HEIGHT / 2 + 80);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true;
                gameOver = false;
            } else if (gameOver) {
                restartGame();
            } else {
                bird.jump();
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    private void restartGame() {
        bird = new Bird(WIDTH / 8, HEIGHT / 2);
        pipes.clear();
        score = 0;
        gameOver = false;
        gameStarted = true;
        addPipes();
    }
}

class Bird {
    private int x;
    private int y;
    private int width = 30;
    private int height = 30;
    private int velocity = 0;
    private static final int GRAVITY = 2;
    
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void update() {
        velocity += GRAVITY;
        y += velocity;
    }
    
    public void jump() {
        velocity = -13;
    }
    
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawOval(x, y, width, height);
    }
    
    public boolean collidesWith(Pipe pipe) {
        return x + width > pipe.getX() && 
               x < pipe.getX() + pipe.getWidth() &&
               (y < pipe.getTopHeight() || y + height > pipe.getTopHeight() + pipe.getGapHeight());
    }
    
    public int getY() {
        return y;
    }
    
    public int getHeight() {
        return height;
    }
}

class Pipe {
    private int x;
    private int topHeight;
    private int width;
    private int gapHeight;
    private static final int PIPE_SPEED = -4;
    
    public Pipe(int x, int topHeight, int width, int gapHeight) {
        this.x = x;
        this.topHeight = topHeight;
        this.width = width;
        this.gapHeight = gapHeight;
    }
    
    public void update() {
        x += PIPE_SPEED;
    }
    
    public void draw(Graphics2D g) {
        // Cano superior
        g.fillRect(x, 0, width, topHeight);
        // Cano inferior
        g.fillRect(x, topHeight + gapHeight, width, 600 - topHeight - gapHeight);
    }
    
    public int getX() {
        return x;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getTopHeight() {
        return topHeight;
    }
    
    public int getGapHeight() {
        return gapHeight;
    }
}
