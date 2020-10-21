/*rooms.add(0, new Room(1, 2, true, 500, WingType.EAST));
		rooms.add(1, new Room(6, 4, false, 1000, WingType.EAST));

		wings.add(new Wing(WingType.EAST, rooms));

		floors.add(new Floor(1, 1, wings));
		floors.add(new Floor(2, 1, wings));

		hotels.add(new Hotel(1, "Hilton", "MiddleOf St. Nowh Ere 16.", 5, floors));*/

package com.meiit.webalk.reservation;

import com.meiit.webalk.reservation.domain.Reservation;
import com.meiit.webalk.reservation.domain.Room;
import com.meiit.webalk.reservation.service.ReservationService;
import com.meiit.webalk.reservation.view.View;

public class App {

	ReservationService reservationService;
	App app;
	View view;

	public static void main(String[] args) {

		try {
			App app = new App(new ReservationService(), new View());
			app.createBookingPerson();
			app.book();
			app.checkIn();
			app.checkOut();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public App(ReservationService res, View view) {
		this.reservationService = res;
		this.view = view;
	}

	public void createBookingPerson() {
		reservationService.saveBookingPerson(view.readBookingPerson());
	}

	public void book() {
		Room r = null;
		while (r == null) {
			r = view.selectRoom(reservationService.findAllHotels());
			if (r != null) {
				reservationService.saveReservation(new Reservation(reservationService.findBookingPerson().getName(), r));
				view.printReservationSaved();
			}
	
			
		}
		;

	}

	public void checkIn() {
		view.printCheckIn();
	}

	public void checkOut() {
		view.printCheckOut();
	}

}