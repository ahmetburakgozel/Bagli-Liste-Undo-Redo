package undoredo;
import java.util.Scanner;
public class UndoRedo {

    public static void main(String[] args) {
        // Son undoları tutan liste:
        String[] lastRedos = new String[5];
        // Ana node
        Node root = new Node("root", null, null);
        Scanner reader = new Scanner(System.in);
        // Sonsuz döngü oluşturuyoruz program için
        while(true){
            // Aldığımız komut hangisi ise o ifin içine girip o işlemleri yapıyoruz
            System.out.println("Komut gir: ");
            String command = reader.nextLine();
            if(command.equals("undo")){
                // Döngü ile en son node' a kadar ilerliyoruz
                Node currentNode = root;
                while(currentNode.next != null){
                    currentNode = currentNode.next;
                }
                // Son node' un datasını alıyoruz
                String data = currentNode.data;
                if(currentNode.pre != null){ // Eğer son node root node değilse (root node çıkartılamaz çünkü)
                    // Linked List' ten node u çıkartıyoruz
                    currentNode.pre.next = null;
                    // Son undolara undo işlemini ekliyoruz
                    boolean flag = false;
                    for(int i = 0; i < 5; i++){
                        if(lastRedos[i] == null){
                            lastRedos[i] = data;
                            flag = true;
                            break;
                        }
                    }
                    // Eğer undolar listesi dolu ise, herşeyi bir adım geri atıyoruz ve sonuncuya çıkardığımız undoyu ekliyoruz
                    if(!flag){
                        for(int i = 0; i < 4; i++){
                            lastRedos[i] = lastRedos[i + 1];
                        }
                        lastRedos[4] = data;
                    }
                }else{
                    System.out.println("Root node' u silemezsin.");
                }
            }else if(command.equals("redo")){
                boolean flag = false;
                for(int i = 4; i >= 0; i--){
                    // Son undo işlemini buluyoruz :
                    if(lastRedos[i] != null){
                        // Son node' a gidiyoruz yine:
                        Node currentNode = root;
                        while(currentNode.next != null){
                            currentNode = currentNode.next;
                        }
                        // Son undodaki datayı tekrardan linked list' e ekliyoruz:
                        currentNode.next = new Node(lastRedos[i], null, currentNode);
                        // Undoyu listeden çıkartıyoruz:
                        lastRedos[i] = null;
                        flag =true;
                        break;
                    }
                }
                // Eğer undo işlemi yoksa uyarı veriyoruz.
                if(!flag){
                    System.out.println("Geri alınmış işlem yok!");
                }
            }else if(command.equals("yaz")){
                // Tüm nodelarda dolaşıyor ve datalarını ekrana yazdırıyoruz.
                Node currentNode = root;
                int i = 0;
                while(currentNode.next != null){
                    System.out.println(i++ + ") " + currentNode.data);
                    currentNode = currentNode.next;
                }
                System.out.println(i + ") " + currentNode.data);
            }else{
                // Eğer yukarıdaki komutlar dışında bir şey girildiyse yeni veri eklenecektir. Bu yüzden son undolar listesini boşaltıyoruz:
                for(int i = 0; i < 5; i++){
                    lastRedos[i] = null;
                }
                // Son node' a gidip yeni node' u girilen data ile beraber ekliyoruz:
                Node currentNode = root;
                while(currentNode.next != null){
                    currentNode = currentNode.next;
                }
                currentNode.next = new Node(command, null, currentNode);
            }
        }
    }
    
}
