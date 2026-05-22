package hust.soict.vp.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();
    private String content = "";

    public Book() {
        super();
    }

    public Book(String title) {
        super(title);
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        if (authors != null) {
            for (String author : authors) {
                addAuthor(author);
            }
        }
    }

    public void addAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            System.out.println("Invalid author name.");
            return;
        }

        if (authors.contains(authorName)) {
            System.out.println("Author already exists.");
            return;
        }

        authors.add(authorName);
        System.out.println("Author has been added.");
    }

    public void removeAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            System.out.println("Author does not exist.");
            return;
        }

        authors.remove(authorName);
        System.out.println("Author has been removed.");
    }

    public List<String> getAuthors() {
        return new ArrayList<String>(authors);
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public int getContentLength() {
        String normalized = content.trim();
        if (normalized.isEmpty()) {
            return 0;
        }

        return normalized.split("\\s+").length;
    }

    @Override
    public String toString() {
        return String.format(
                "Book - id: %d - %s - %s - authors: %s - content length: %d: %.2f $",
                getId(),
                getTitle() != null ? getTitle() : "",
                getCategory() != null ? getCategory() : "",
                authors,
                getContentLength(),
                getCost()
        );
    }
}
