/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Track {

    private int id;
    private String name;
    private int AlbumId;
    private int MediaTypeId;
    private int GenreId;
    private String Composer;
    private int Milliseconds;
    private int Bytes;
    private float UnitPrice;

    public Track() {
    }

    public Track(int Id, String name, int AlbumId, int MediaTypeId, int GenreId, String Composer, int Milliseconds, int Bytes, float UnitPrice) {
        this.id = Id;
        this.name = name;
        this.AlbumId = AlbumId;
        this.MediaTypeId = MediaTypeId;
        this.GenreId = GenreId;
        this.Composer = Composer;
        this.Milliseconds = Milliseconds;
        this.Bytes = Bytes;
        this.UnitPrice = UnitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(int AlbumId) {
        this.AlbumId = AlbumId;
    }

    public int getMediaTypeId() {
        return MediaTypeId;
    }

    public void setMediaTypeId(int MediaTypeId) {
        this.MediaTypeId = MediaTypeId;
    }

    public int getGenreId() {
        return GenreId;
    }

    public void setGenreId(int GenreId) {
        this.GenreId = GenreId;
    }

    public String getComposer() {
        return Composer;
    }

    public void setComposer(String Composer) {
        this.Composer = Composer;
    }

    public int getMilliseconds() {
        return Milliseconds;
    }

    public void setMilliseconds(int Milliseconds) {
        this.Milliseconds = Milliseconds;
    }

    public int getBytes() {
        return Bytes;
    }

    public void setBytes(int Bytes) {
        this.Bytes = Bytes;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    @Override
    public String toString() {
        return "-" + "Id=" + id + ", name=" + name + ", AlbumId=" + AlbumId + ", MediaTypeId=" + MediaTypeId + ", GenreId=" + GenreId + ", Composer=" + Composer + ", Milliseconds=" + Milliseconds + ", Bytes=" + Bytes + ", UnitPrice=" + UnitPrice;
    }

}
