package com.codepath.apps.simpletwitterclient.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Items")
public class Item extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;
    // This is a regular field
    @Column(name = "Name", index = true)
    public String name;
//    @Column(name = "ScreenName")
//    public String screenName;
//    @Column(name = "ProfileImage")
//    public String profileImage;
//    @Column(name = "Body")
//    public String body;
//    @Column(name = "TimeStamp")
//    public String timeStamp;
//    @Column(name = "LocalId")
//    public long localId;
//    @Column(name = "LocalUserId")
//    public long localUId;

    // This is an association to another activeandroid model
    @Column(name = "Category", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE, index = true)
    public Category category;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Item(){
        super();
    }

    public Item(int remoteId, String name) {
            //, String screenName, String profileImage, String body, String timeStamp, long localId, long locaUlId, Category category){
        super();
        this.remoteId = remoteId;
        this.name = name;
//        this.screenName = screenName;
//        this.profileImage = profileImage;
//        this.body = body;
//        this.timeStamp = timeStamp;
//        this.localId = localId;
//        this.localUId = locaUlId;
//        this.category = category;
    }

    public static List<Item> getAll(Category category) {
        // This is how you execute a query
        return new Select()
                .from(Item.class)
                .where("Category = ?", category.getId())
                .orderBy("Name ASC")
                .execute();
    }
}
