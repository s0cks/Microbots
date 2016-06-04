(begin
    ; Movement
    (define move-west (macro (value) (move "west" value)))
    (define move-east (macro (value) (move "east" value)))
    (define move-north (macro (value) (move "north" value)))
    (define move-south (macro (value) (move "south" value)))
    ; Maths
    (define PI 3.14159265359)
    (define TAU (* 2 PI))
    (define square (macro (args) (* args args)))
    ; IO
    (define newline (macro (args) (display "\n")))
    ; ====================================================
    ;
    ;                   MicroOS v0.0.1.0
    ;
    ; ====================================================
    (define red 255)
    (define main-loop
        (lambda (args)
            (move-west 100)
            (set-color red 0 0)
            (if (> red 0)
                (set! red (- red 1))
                (set! red 255))
            (main-loop)))
    (cls)
    (set-cursor-pos 1 1)
    (display "MicroOS v1.0.0.0") (newline)
    (main-loop))