import java.util.Scanner;

interface Playable {

    String play();

    String play(int fromSecond);

    String pause();
}

abstract class MediaFile {

    private static int counter = 1000;

    private final String fileId;

    public MediaFile() {

        counter++;
        fileId = "MF-" + counter;
    }

    public abstract String getFormatInfo();

    public String getFileId() {

        return fileId;
    }
}

class AudioFile extends MediaFile
        implements Playable {

    private String title;

    public AudioFile(String title) {

        this.title = title;
    }

    @Override
    public String play() {

        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {

        return "Playing " + title
                + " from " + fromSecond + " seconds";
    }

    @Override
    public String pause() {

        return "Paused audio: " + title;
    }

    @Override
    public String getFormatInfo() {

        return "Audio file, ID: "
                + getFileId();
    }
}

class Podcast implements Playable {

    private String showName;
    private int episodeNumber;

    public Podcast(String showName,
                   int episodeNumber) {

        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {

        return "Streaming episode "
                + episodeNumber
                + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {

        return "Streaming episode "
                + episodeNumber
                + " of " + showName
                + " from " + fromSecond
                + " seconds";
    }

    @Override
    public String pause() {

        return "Paused podcast: "
                + showName;
    }
}

public class MediaDemo {

    static void launchAll(Playable[] items) {

        for (int i = 0; i < items.length; i++) {

            System.out.println(items[i].play());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter audio title: ");
        String title = sc.nextLine();

        System.out.print("Enter podcast name: ");
        String showName = sc.nextLine();

        System.out.print("Enter episode number: ");
        int episodeNumber = sc.nextInt();

        System.out.print("Enter starting second: ");
        int second = sc.nextInt();

        AudioFile audio =
                new AudioFile(title);

        Podcast podcast =
                new Podcast(showName, episodeNumber);

        System.out.println();
        System.out.println(audio.play());

        System.out.println(
                audio.play(second));

        System.out.println(
                audio.getFormatInfo());

        System.out.println();
        System.out.println(podcast.play());

        System.out.println(
                podcast.play(second));

        // Upcasting
        Playable ref = audio;

        System.out.println();
        System.out.println("Through Playable reference:");
        System.out.println(ref.play());

        System.out.println();
        System.out.println("Launching all:");

        Playable[] items = {
            ref,
            podcast
        };

        launchAll(items);

        sc.close();
    }
}
