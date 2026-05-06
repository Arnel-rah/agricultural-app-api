package edu.hei.school.agricultural.controller;

import edu.hei.school.agricultural.controller.dto.CreateActivityMemberAttendance;
import edu.hei.school.agricultural.exception.BadRequestException;
import edu.hei.school.agricultural.exception.NotFoundException;
import edu.hei.school.agricultural.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/collectivities/{id}/activities/{activityId}/attendance")
    public ResponseEntity<?> addAttendance(
            @PathVariable String id,
            @PathVariable String activityId,
            @RequestBody List<CreateActivityMemberAttendance> attendances) {
        try {
            return ResponseEntity.status(CREATED).body(attendanceService.addAttendance(id, activityId, attendances));
        } catch (BadRequestException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/collectivities/{id}/activities/{activityId}/attendance")
    public ResponseEntity<?> getAttendance(@PathVariable String id, @PathVariable String activityId) {
        try {
            return ResponseEntity.status(OK).body(attendanceService.getAttendance(id, activityId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}