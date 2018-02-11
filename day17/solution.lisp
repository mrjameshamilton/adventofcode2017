(defun part1 (step) 
	(setq max 2017)
	(setq buffer (part1_r step (1+ max) 0 1 '(0)))
	(nth (1+ (position max buffer)) buffer)
)

(defun part1_r (step max curr-position curr-value buffer)
	(setq index (mod (+ curr-position step) (length buffer)))
	(if (= curr-value max) buffer (part1_r step max (1+ index) (1+ curr-value) (insert-after buffer index curr-value)))
)

(defun insert-after (list index value) 
	(append (subseq list 0 (1+ index)) (cons value (cdr (nthcdr index list))))
)

(defun part2 (step max)
	(loop for i from 0 to max with pos = 0 with value-after-zero = 0
		do (if (= 1 (1+ pos)) (setq value-after-zero i))
		do (setq pos (mod (+ (1+ pos) step) (1+ i)))
		finally (return value-after-zero)
	)
)

(setq result (part1 3))
(format t "~a expected: 638, actual: ~a~%" (if (= 638 result) "OK" "FAIL") result)
(format t "~a~%" (part1 376))
(setq result (part2 3 9))
(format t "~a expected: 9, actual: ~a~%" (if (= 9 result) "OK" "FAIL") result)
(format t "~a~%" (part2 376 50000000))
