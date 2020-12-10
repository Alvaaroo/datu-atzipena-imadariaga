/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

public class PlaylistTrack implements Serializable{

    private int PlaylistId;
    private int TrackId;

    public PlaylistTrack() {
    }

    public PlaylistTrack(int PlaylistId, int TrackId) {
        this.PlaylistId = PlaylistId;
        this.TrackId = TrackId;
    }

    public int getPlaylistId() {
        return PlaylistId;
    }

    public void setPlaylistId(int PlaylistId) {
        this.PlaylistId = PlaylistId;
    }

    public int getTrackId() {
        return TrackId;
    }

    public void setTrackId(int TrackId) {
        this.TrackId = TrackId;
    }

    @Override
    public String toString() {
        return "PlaylistTrack{" + "PlaylistId=" + PlaylistId + ", TrackId=" + TrackId + '}';
    }

}
