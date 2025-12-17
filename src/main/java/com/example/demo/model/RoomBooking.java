// package com.example.entity;   // change package name if needed

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;

// import java.time.LocalDate;
// import java.util.Set;

// @Entity
// public class RoomBooking {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     // Many bookings -> One guest
//     @ManyToOne
//     @JoinColumn(name = "guest_id", nullable = false)
//     private Guest guest;

//     private String roomNumber;

//     private LocalDate checkInDate;
//     private LocalDate checkOutDate;

//     // defaults to true
//     private Boolean active = true;

//     // Many bookings -> Many roommates
//     @ManyToMany
//     @JoinTable(
//         name = "room_booking_roommates",
//         joinColumns = @JoinColumn(name = "booking_id"),
//         inverseJoinColumns = @JoinColumn(name = "guest_id")
//     )
//     private Set<Guest> roommates;

//     // ✅ Empty constructor (required by JPA)
//     public RoomBooking() {
//     }

//     // ✅ Parameterized constructor
//     public RoomBooking(Guest guest, String roomNumber,
//                        LocalDate checkInDate, LocalDate checkOutDate,
//                        Boolean active, Set<Guest> roommates) {
//         this.guest = guest;
//         this.roomNumber = roomNumber;
//         this.checkInDate = checkInDate;
//         this.checkOutDate = checkOutDate;
//         this.active = (active != null) ? active : true;
//         this.roommates = roommates;
//     }

//     // ✅ Rule: checkInDate < checkOutDate
//     @PrePersist
//     @PreUpdate
//     private void validateDates() {
//         if (checkInDate != null && checkOutDate != null &&
//             !checkInDate.isBefore(checkOutDate)) {
//             throw new IllegalArgumentException(
//                 "checkInDate must be before checkOutDate"
//             );
//         }
//     }

//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }

//     public Guest getGuest() {
//         return guest;
//     }

//     public void setGuest(Guest guest) {
//         this.guest = guest;
//     }

//     public String getRoomNumber() {
//         return roomNumber;
//     }

//     public void setRoomNumber(String roomNumber) {
//         this.roomNumber = roomNumber;
//     }

//     public LocalDate getCheckInDate() {
//         return checkInDate;
//     }

//     public void setCheckInDate(LocalDate checkInDate) {
//         this.checkInDate = checkInDate;
//     }

//     public LocalDate getCheckOutDate() {
//         return checkOutDate;
//     }

//     public void setCheckOutDate(LocalDate checkOutDate) {
//         this.checkOutDate = checkOutDate;
//     }

//     public Boolean getActive() {
//         return active;
//     }

//     public void setActive(Boolean active) {
//         this.active = active;
//     }

//     public Set<Guest> getRoommates() {
//         return roommates;
//     }

//     public void setRoommates(Set<Guest> roommates) {
//         this.roommates = roommates;
//     }
// }
