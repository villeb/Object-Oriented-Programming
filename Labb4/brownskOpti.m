
X =  load('outputs.csv');
T = 1000; % Totala tiden
dt = 50; % tidssteget
t = [0:dt:T];

for i = 1:size(X,1)
    

%plot(X(i,2:2:end),X(i,3:2:end),'*');
%drawnow 
Mx(i) = max(X(i,2:2:end));
My(i) = max(X(i,3:2:end));

mx(i) = mean(X(i,2:2:end));
my(i) = mean(X(i,3:2:end));

sx(i) = std(X(i,2:2:end));
sy(i) = std(X(i,3:2:end));
end
% plot(t,Mx);
% hold on;
% plot(t,My);
% plot(t,my);
% 
plot(t,sx)
grid on;