public class jogodaForca {
    
    public static void desenharForca(int erros) {
        String[][] estagios = {
            // Estágio 0 - Forca vazia
            {
                "  +---+",
                "  |   |",
                "      |",
                "      |",
                "      |",
                "      |",
                "=========",
            },
            // Estágio 1 - Cabeça
            {
                "  +---+",
                "  |   |",
                "  O   |",
                "      |",
                "      |",
                "      |",
                "=========",
            },
            // Estágio 2 - Corpo
            {
                "  +---+",
                "  |   |",
                "  O   |",
                "  |   |",
                "      |",
                "      |",
                "=========",
            },
            // Estágio 3 - Braço esquerdo
            {
                "  +---+",
                "  |   |",
                "  O   |",
                " \\|   |",
                "      |",
                "      |",
                "=========",
            },
            // Estágio 4 - Braço direito
            {
                "  +---+",
                "  |   |",
                "  O   |",
                " \\|/  |",
                "      |",
                "      |",
                "=========",
            },
            // Estágio 5 - Perna esquerda
            {
                "  +---+",
                "  |   |",
                "  O   |",
                " \\|/  |",
                " /    |",
                "      |",
                "=========",
            },
            // Estágio 6 - Game Over (perna direita)
            {
                "  +---+",
                "  |   |",
                "  O   |",
                " \\|/  |",
                " / \\  |",
                "      |",
                "=========",
            }
        };
        
        int indice = Math.min(erros, estagios.length - 1);
        for (String linha : estagios[indice]) {
            System.out.println(linha);
        }
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String[] palavras = {
                         "abacaxi", "borboleta", "cachorro", "dinossauro", "elefante",
    "formiga", "girassol", "hipopotamo", "igreja", "jacare",
    "kiwi", "limao", "macaco", "navio", "ovelha",
    "pinguim", "queijo", "rato", "sapo", "tartaruga",
    "urso", "vaca", "xicara", "zebra", "aviao",
    "bola", "casa", "dado", "escola", "faca",
    "gato", "hiena", "ilha", "janela", "lapis",
    "mesa", "nuvem", "olho", "pato", "roda"
        };
        String palavraSecreta = palavras[new java.util.Random().nextInt(palavras.length)];
        char[] letrasDescobertas = new char[palavraSecreta.length()];
        for (int i = 0; i < letrasDescobertas.length; i++) {
            letrasDescobertas[i] = '_';
        }
        int tentativasRestantes = 6;
        java.util.Set<Character> letrasErradas = new java.util.HashSet<>();

        System.out.println("=== BEM-VINDO AO JOGO DA FORCA ===\n");

        while (tentativasRestantes > 0) {
            // Desenhar a forca
            int erros = 6 - tentativasRestantes;
            desenharForca(erros);
            
            System.out.println("\nPalavra: " + String.valueOf(letrasDescobertas));
            System.out.println("Tentativas restantes: " + tentativasRestantes);
            System.out.println("Letras erradas: " + letrasErradas);
            System.out.print("Digite uma letra: ");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Entrada inválida. Digite apenas uma letra.\n");
                continue;
            }
            
            char letra = input.charAt(0);
            if (letrasErradas.contains(letra) || new String(letrasDescobertas).indexOf(letra) >= 0) {
                System.out.println("Você já tentou essa letra. Tente outra.\n");
                continue;
            }
            
            boolean acertou = false;
            for (int i = 0; i < palavraSecreta.length(); i++) {
                if (palavraSecreta.charAt(i) == letra) {
                    letrasDescobertas[i] = letra;
                    acertou = true;
                }
            }
            
            if (!acertou) {
                letrasErradas.add(letra);
                tentativasRestantes--;
                System.out.println("❌ Letra errada!\n");
            } else {
                System.out.println("✓ Acertou!\n");
            }
            
            if (String.valueOf(letrasDescobertas).equals(palavraSecreta)) {
                System.out.println("╔════════════════════════════════════╗");
                System.out.println("║  PARABÉNS! VOCÊ GANHOU!            ║");
                System.out.println("║  Palavra: " + palavraSecreta.toUpperCase() + " ".repeat(23 - palavraSecreta.length()) + "║");
                System.out.println("╚════════════════════════════════════╝");
                return;
            }
        }
        
        desenharForca(6);
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  GAME OVER!                        ║");
        System.out.println("║  Você perdeu...                    ║");
        System.out.println("║  Palavra: " + palavraSecreta.toUpperCase() + " ".repeat(23 - palavraSecreta.length()) + "║");
        System.out.println("╚════════════════════════════════════╝");
    }
}