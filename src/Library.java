import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
     private String name;
     private String address;
     private Map<String, LibraryItem> items;
     private Map<String, Person> people;

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.items = new HashMap<>();
        this.people = new HashMap<>();
    }

    public boolean addItem(LibraryItem item){
        if(item == null){
            System.out.println("Cannot add null item to library");
            return false;
        }
        if(items.containsKey(item.getId())){
            System.out.println("Item with ID " + item.getId() + " already exists in the library.");
            return false;
        }
        items.put(item.getId(),item);
        System.out.println("Item added successfully: " + item.getTitle());
        return true;
    }
    public boolean removeItem(String itemId){
        if(!items.containsKey(itemId)){
            System.out.println("Item with ID " + itemId + " not found in the library.");
            return false;
        }
        LibraryItem item = items.get(itemId);
        if(item.isCheckedOut()){
            System.out.println("Cannot remove item that is currently checked out.");
            return false;
        }
        items.remove(itemId);
        System.out.println("Item removed successfully: " + item.getTitle());
        return true;
    }
    public LibraryItem findItem(String itemId){
        return items.get(itemId);
    }
    public List<LibraryItem> searchItemsByTitle(String title){
        if(title == null || title.trim().isEmpty()){
            return new ArrayList<>();
        }
        String searchTerm = title.toLowerCase();
        return items.values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
    public List<LibraryItem> searchItemsByType(String type){
        if (type == null || type.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String searchType = type.toLowerCase();
        return items.values().stream()
                .filter(item -> item.getItemType().toLowerCase().equals(searchType))
                .collect(Collectors.toList());
    }
    public boolean registerPerson(Person person){
        if(person == null){
            System.out.println("Cannot register null person.");
            return false;
        }
        if(people.containsKey(person.getId())){
            System.out.println("Person with ID " + person.getId() + " already registered.");
            return false;
        }
        people.put(person.getId(), person);
        System.out.println(person.getRole() + " registered successfully: " + person.getName());
        return true;
    }
    public Person findPerson(String id){
        return people.get(id);
    }
    public List<LibraryItem> getAvailableItems() {
        return items.values().stream()
                .filter(item -> !item.isCheckedOut())
                .collect(Collectors.toList());
    }
    public List<LibraryItem> getCheckedOutItems() {
        return items.values().stream()
                .filter(LibraryItem::isCheckedOut)
                .collect(Collectors.toList());
    }

    public void displayLibraryStats() {
        int totalItems = items.size();
        int availableItems = getAvailableItems().size();
        int checkedOutItems = getCheckedOutItems().size();

        int totalPatrons = (int) people.values().stream()
                .filter(p -> p instanceof Patron)
                .count();

        int totalStaff = (int) people.values().stream()
                .filter(p -> p instanceof LibraryStaff)
                .count();

        // Count of each item type
        int books = (int) items.values().stream()
                .filter(item -> item.getItemType().equalsIgnoreCase("Book"))
                .count();

        int magazines = (int) items.values().stream()
                .filter(item -> item.getItemType().equalsIgnoreCase("Magazine"))
                .count();

        int dvds = (int) items.values().stream()
                .filter(item -> item.getItemType().equalsIgnoreCase("DVD"))
                .count();

        System.out.println("\n===== " + name + " Library Statistics =====");
        System.out.println("Address: " + address);
        System.out.println("Total Items: " + totalItems);
        System.out.println("  - Available: " + availableItems);
        System.out.println("  - Checked Out: " + checkedOutItems);
        System.out.println("\nItem Types:");
        System.out.println("  - Books: " + books);
        System.out.println("  - Magazines: " + magazines);
        System.out.println("  - DVDs: " + dvds);
        System.out.println("\nRegistered Users:");
        System.out.println("  - Patrons: " + totalPatrons);
        System.out.println("  - Staff: " + totalStaff);
        System.out.println("=====================================");
    }
    public List<LibraryItem> searchItemsByCreator(String creator) {
        if (creator == null || creator.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String searchTerm = creator.toLowerCase();
        List<LibraryItem> results = new ArrayList<>();

        for (LibraryItem item : items.values()) {
            if (item instanceof Book) {
                Book book = (Book) item;
                if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                    results.add(item);
                }
            } else if (item instanceof DVD) {
                DVD dvd = (DVD) item;
                if (dvd.getDirector().toLowerCase().contains(searchTerm)) {
                    results.add(item);
                }
            }
        }

        return results;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, LibraryItem> getItems() {
        return items;
    }

    public Map<String, Person> getPeople() {
        return people;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setItems(Map<String, LibraryItem> items) {
        this.items = items;
    }

    public void setPeople(Map<String, Person> people) {
        this.people = people;
    }
}
