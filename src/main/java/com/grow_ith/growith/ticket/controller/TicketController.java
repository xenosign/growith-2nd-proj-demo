package com.grow_ith.growith.ticket.controller;

import com.grow_ith.growith.ticket.dto.TicketRequestDto;
import com.grow_ith.growith.ticket.dto.TicketResponseDto;
import com.grow_ith.growith.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    // 티켓 예약
    @PostMapping("/reserve")
    public ResponseEntity<TicketResponseDto> reserveTicket(@RequestBody TicketRequestDto requestDto) {
        TicketResponseDto response = ticketService.reserveTicket(requestDto);
        return ResponseEntity.ok(response);
    }

    // 티켓 예약 조회
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDto> getTicket(@PathVariable Long ticketId) {
        TicketResponseDto response = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(response);
    }

    // 사용자별 티켓 예약 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketResponseDto>> getUserTickets(@PathVariable Long userId) {
        List<TicketResponseDto> tickets = ticketService.getUserTickets(userId);
        return ResponseEntity.ok(tickets);
    }

    // 티켓 예약 취소
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long ticketId) {
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.noContent().build();
    }

    // 모든 티켓 예약 조회 (관리자용)
    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        List<TicketResponseDto> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
}