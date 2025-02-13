package com.ProjetoWeb.ProjetoWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoWeb.ProjetoWeb.dto.ReservasDTO;
import com.ProjetoWeb.ProjetoWeb.service.ReservasService;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @PostMapping
    public ResponseEntity<?> reservarArea(@RequestBody ReservasDTO reservasDTO) {
        try {
            ReservasDTO reserva = reservasService.reservarArea(reservasDTO);
            return new ResponseEntity<>(reserva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservasDTO>> listarTodasReservas() {
        List<ReservasDTO> reservas = reservasService.listarTodasReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirReserva(@PathVariable Long id) {
        try {
            String resposta = reservasService.excluirReserva(id);
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
