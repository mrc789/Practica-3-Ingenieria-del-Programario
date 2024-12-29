package services.smartfeatures;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.*;

class ArduinoMicroControllerStubTest {

    private services.smartfeatures.stubs.ArduinoMicroControllerStub stub;

    @BeforeEach
    void setUp() {
        stub = new services.smartfeatures.stubs.ArduinoMicroControllerStub();
    }

    @Test
    void testSetBTconnection() throws ConnectException {
        assertFalse(stub.isConnected());
        stub.setBTconnection();
        assertTrue(stub.isConnected());
    }

    @Test
    void testSetBTconnectionThrowsExceptionWhenAlreadyConnected() throws ConnectException {
        stub.setBTconnection();
        assertThrows(ConnectException.class, stub::setBTconnection);
    }

    @Test
    void testStartDriving() throws Exception {
        stub.setBTconnection();
        assertFalse(stub.isDriving());
        stub.startDriving();
        assertTrue(stub.isDriving());
    }

    @Test
    void testStartDrivingThrowsExceptionWhenNotConnected() {
        assertThrows(ConnectException.class, stub::startDriving);
    }

    @Test
    void testStartDrivingThrowsExceptionWhenAlreadyDriving() throws Exception {
        stub.setBTconnection();
        stub.startDriving();
        assertThrows(ProceduralException.class, stub::startDriving);
    }

    @Test
    void testStopDriving() throws Exception {
        stub.setBTconnection();
        stub.startDriving();
        assertTrue(stub.isDriving());
        stub.stopDriving();
        assertFalse(stub.isDriving());
    }

    @Test
    void testStopDrivingThrowsExceptionWhenNotDriving() throws Exception {
        stub.setBTconnection();
        assertThrows(ProceduralException.class, stub::stopDriving);
    }

    @Test
    void testStopDrivingThrowsExceptionWhenNotConnected() {
        assertThrows(ConnectException.class, stub::stopDriving);
    }

    @Test
    void testUndoBTconnection() {
        stub.undoBTconnection();
        assertFalse(stub.isConnected());
        assertFalse(stub.isDriving());
    }
}
