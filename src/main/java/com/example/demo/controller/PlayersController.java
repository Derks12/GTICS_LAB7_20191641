package com.example.demo.controller;

import com.example.demo.entity.Players;
import com.example.demo.repository.PlayersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/players")
public class PlayersController {

    final PlayersRepository playersRepository;

    public PlayersController(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }


    //listar
    @GetMapping(value = {"/list",""})
    public List<Players> listaPlayers() {
            return playersRepository.listarMmr() ;
    }

    //obtener
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String, Object>> obtenerPlayers(@PathVariable("id") BigInteger idStr) {

        try {
            int id = Integer.parseInt(idStr.toString());
            Optional<Players> byId = playersRepository.findById(id);

            HashMap<String, Object> responseJson = new HashMap<>();

            if (byId.isPresent()) {
                responseJson.put("result", "ok");
                responseJson.put("producto", byId.get());
            } else {
                responseJson.put("result", "no existe");
            }
            return ResponseEntity.ok(responseJson);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }


    //crear
    @PostMapping(value = {"","/"})
    public ResponseEntity<HashMap<String, Object>> addPlayer(@RequestBody Players player, @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        playersRepository.save(player);
        if (fetchId) {
            responseJson.put("id", player.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);

    }


    //borra
    @DeleteMapping(value = "/players/{id}")
    public ResponseEntity<HashMap<String, Object>> deletePlayer(@PathVariable(value = "id") BigInteger idStr) {

        try {
            int id = Integer.parseInt(idStr.toString());

            HashMap<String, Object> responseJson = new HashMap<>();

            if (playersRepository.existsById(id)) {
                playersRepository.deleteById(id);
                responseJson.put("estado", "eliminado");
                return ResponseEntity.ok(responseJson);
            }else {
                responseJson.put("estado", "no hay player");
                responseJson.put("msg", "El ID enviado no existe");
            }
            return ResponseEntity.ok(responseJson);

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }


    }



}
