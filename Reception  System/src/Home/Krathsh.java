package Home;

import java.util.Date;

public class Krathsh {

    private int resrvId;
    private int custmrId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;

    public Krathsh(int resrvId, int custmrId, int roomId, Date checkInDate, Date checkOutDate) {
        this.resrvId = resrvId;
        this.custmrId = custmrId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Krathsh() {
    }

    public int getResrvId() {
        return resrvId;
    }

    public void setResrvId(int resrvId) {
        this.resrvId = resrvId;
    }

    public int getCustmrId() {
        return custmrId;
    }

    public void setCustmrId(int custmrId) {
        this.custmrId = custmrId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Krathsh{" +
                "resrvId=" + resrvId +
                ", custmrId=" + custmrId +
                ", roomId=" + roomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

