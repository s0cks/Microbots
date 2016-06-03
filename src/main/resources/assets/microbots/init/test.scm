(begin
    (define move-forward (lambda (value) (move "west" value)))
    (define move-backward (lambda (value) (move "east" value)))
    (move-forward 10))