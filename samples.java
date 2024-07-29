2. Strategy Pattern
The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It allows the algorithm to vary independently from clients that use it.
Use Case: Payment System
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class ShoppingCart {
    private int total = 0;

    public void addItem(String item, int price) {
        total += price;
    }

    public void pay(PaymentStrategy paymentStrategy) {
        paymentStrategy.pay(total);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 15);
        cart.addItem("Pen", 5);

        PaymentStrategy creditCardPayment = new CreditCardPayment();
        cart.pay(creditCardPayment);

        PaymentStrategy paypalPayment = new PayPalPayment();
        cart.pay(paypalPayment);
    }
}
Creational Design Patterns
1. Singleton Pattern
The Singleton Pattern ensures a class has only one instance and provides a global point of access to it.
Use Case: Logger
class Logger {
    private static Logger instance;

    private Logger() {
        // private constructor to prevent instantiation
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println(logger1 == logger2);  // True

        logger1.log("This is a log message.");
        logger2.log("This is another log message.");
    }
}
The Factory Pattern defines an interface for creating an object but allows subclasses to alter the type of objects that will be created.
Use Case: Document Creator
interface Document {
    void create();
}

class WordDocument implements Document {
    @Override
    public void create() {
        System.out.println("Word document created.");
    }
}

class PDFDocument implements Document {
    @Override
    public void create() {
        System.out.println("PDF document created.");
    }
}

class DocumentFactory {
    public static Document createDocument(String docType) {
        if (docType.equalsIgnoreCase("word")) {
            return new WordDocument();
        } else if (docType.equalsIgnoreCase("pdf")) {
            return new PDFDocument();
        } else {
            throw new IllegalArgumentException("Unknown document type");
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Document doc1 = DocumentFactory.createDocument("word");
        doc1.create();

        Document doc2 = DocumentFactory.createDocument("pdf");
        doc2.create();
    }
}
Structural Design Patterns
1. Adapter Pattern
The Adapter Pattern allows the interface of an existing class to be used as another interface. It is often used to make existing classes work with others without modifying their source code.
Use Case: Media Player
interface MediaPlayer {
    void play(String audioType, String fileName);
}

class MP3Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else {
            System.out.println("Invalid media type for MP3 player");
        }
    }
}

class MP4Player {
    public void playMP4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    MP4Player advancedPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new MP4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMP4(fileName);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type");
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("mp4", "video.mp4");
        player.play("avi", "movie.avi");
    }
}
2. Decorator Pattern
The Decorator Pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class.
Use Case: Notification System
interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SMSNotifier implements Notifier {
    private Notifier notifier;

    public SMSNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotifier implements Notifier {
    private Notifier notifier;

    public PushNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
        System.out.println("Sending Push Notification: " + message);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifier(emailNotifier);
        Notifier pushNotifier = new PushNotifier(smsNotifier);

        pushNotifier.send("Hello, this is a notification!");
    }
}

